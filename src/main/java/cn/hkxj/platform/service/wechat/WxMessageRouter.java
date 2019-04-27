package cn.hkxj.platform.service.wechat;

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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author junrong.chen
 * @date 2018/11/17
 */
@Slf4j
public class WxMessageRouter extends WxMpMessageRouter {
    private static final int DEFAULT_THREAD_POOL_SIZE = 100;
    private final WxMpService wxMpService;
    private final List<WxMessageRouterRule> rules = new ArrayList<>();
    private ExecutorService executorService;

    private WxMessageDuplicateChecker messageDuplicateChecker;

    private WxSessionManager sessionManager;

    private WxErrorExceptionHandler exceptionHandler;
	public WxMessageRouter(WxMpService wxMpService) {
		super(wxMpService);
        this.wxMpService = wxMpService;
        this.executorService = Executors.newFixedThreadPool(DEFAULT_THREAD_POOL_SIZE);
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
     */
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
        final List<Future<?>> futures = new ArrayList<>();
        for (final WxMessageRouterRule rule : matchRules) {
            // 返回最后一个非异步的rule的执行结果
            if (rule.isAsync()) {
                futures.add(
                        this.executorService.submit(new Runnable() {
                            @Override
                            public void run() {
                                rule.service(wxMessage, context, mpService, WxMessageRouter.this.sessionManager, WxMessageRouter.this.exceptionHandler);
                            }
                        })
                );
            } else {
                res = rule.service(wxMessage, context, mpService, this.sessionManager, this.exceptionHandler);
                // 在同步操作结束，session访问结束
                log.debug("End session access: async=false, sessionId={}", wxMessage.getFromUser());
                sessionEndAccess(wxMessage);
            }
        }

        if (futures.size() > 0) {
            this.executorService.submit(() -> {
                for (Future<?> future : futures) {
                    try {
                        future.get();
                        log.debug("End session access: async=true, sessionId={}", wxMessage.getFromUser());
                        // 异步操作结束，session访问结束
                        sessionEndAccess(wxMessage);
                    } catch (InterruptedException | ExecutionException e) {
                        log.error("Error happened when wait task finish", e);
                    }
                }
            });
        }
        return res;
    }



}
