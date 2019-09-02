package cn.hkxj.platform.service.wechat;

import cn.hkxj.platform.MDCThreadPool;
import com.alibaba.ttl.threadpool.TtlExecutors;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxErrorExceptionHandler;
import me.chanjar.weixin.common.api.WxMessageDuplicateChecker;
import me.chanjar.weixin.common.api.WxMessageInMemoryDuplicateChecker;
import me.chanjar.weixin.common.session.StandardSessionManager;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.common.util.LogExceptionHandler;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author junrong.chen
 * @date 2018/11/17
 */
@Slf4j
public class WxMessageRouter extends WxMpMessageRouter {
    private final WxMpService wxMpService;
    private final List<WxMessageRouterRule> rules = new ArrayList<>();
    private ExecutorService executorService;

    private WxMessageDuplicateChecker messageDuplicateChecker;

    private WxSessionManager sessionManager;

    private WxErrorExceptionHandler exceptionHandler;
	public WxMessageRouter(WxMpService wxMpService) {
		super(wxMpService);
        this.wxMpService = wxMpService;
        this.executorService = TtlExecutors.getTtlExecutorService(
                new MDCThreadPool(7, 7, 0L, TimeUnit.SECONDS,
                        new LinkedBlockingQueue<>(), r -> new Thread(r, "WechatMessage")));
        this.messageDuplicateChecker = new WxMessageInMemoryDuplicateChecker();
        this.sessionManager = new StandardSessionManager();
        this.exceptionHandler = new LogExceptionHandler();
	}


	@Override
	public WxMessageRouterRule rule() {
		return new WxMessageRouterRule(this);
	}

    List<WxMessageRouterRule> getRules() {
        return this.rules;
    }

    /**
     * 处理微信消息
     *
     * 原来对于异步的支持是将任务提交到线程池中 然后最好get这个结果
     * 这个做法显然是不太好的，如果这个get的动作时间特别长回导致这个线程就block住了
     * 查阅微信的API文档可以发现，客服接口每天允许发50W条的消息，这个量目前来说绝对够用了
     * 现在把所有需要异步的处理提交到线程池让客服接口去返回，可以有效提升系统的吞吐并且把线程池有效管理起来
     */
    @Override
    public WxMpXmlOutMessage route(final WxMpXmlMessage wxMessage, final Map<String, Object> context, WxMpService wxMpService) {
        if (wxMpService == null) {
            wxMpService = this.wxMpService;
        }
        final WxMpService mpService = wxMpService;
        if (isMsgDuplicated(wxMessage)) {
            // 如果是重复消息，那么就不做处理
            return null;
        }

        final List<WxMessageRouterRule> matchRules = new ArrayList<>();
        // 收集匹配的规则
        for (final WxMessageRouterRule rule : this.rules) {
            if (rule.test(wxMessage)) {
                matchRules.add(rule);
                if (!rule.isReEnter()) {
                    break;
                }
            }
        }

        if (matchRules.size() == 0) {
            return null;
        }

        WxMpXmlOutMessage res = null;

        for (final WxMessageRouterRule rule : matchRules) {
            // 返回最后一个非异步的rule的执行结果
            if (rule.isAsync()) {
                this.executorService.submit(() -> {
                    WxMpXmlOutMessage message = rule.service(wxMessage, context, mpService, WxMessageRouter.this.sessionManager, WxMessageRouter.this.exceptionHandler);
                    CustomerMessageService service = new CustomerMessageService(wxMessage, mpService);
                    service.sendMessage(message);
                });
            } else {
                res = rule.service(wxMessage, context, mpService, this.sessionManager, this.exceptionHandler);
                // 在同步操作结束，session访问结束
                log.debug("End session access: async=false, sessionId={}", wxMessage.getFromUser());
                sessionEndAccess(wxMessage);
            }
        }
        return res;
    }



}
