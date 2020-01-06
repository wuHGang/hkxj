package cn.hkxj.platform.controller;


import cn.hkxj.platform.MDCThreadPool;
import cn.hkxj.platform.config.wechat.WechatMpConfiguration;
import cn.hkxj.platform.exceptions.OpenidExistException;
import cn.hkxj.platform.exceptions.PasswordUnCorrectException;
import cn.hkxj.platform.exceptions.UrpEvaluationException;
import cn.hkxj.platform.exceptions.UrpVerifyCodeException;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.pojo.WebResponse;
import cn.hkxj.platform.pojo.constant.ErrorCode;
import cn.hkxj.platform.service.OpenIdService;
import cn.hkxj.platform.service.TeachingEvaluationService;
import cn.hkxj.platform.service.wechat.StudentBindService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@Slf4j
@Controller
public class UserBindingController {
    @Resource
    private HttpSession httpSession;
    @Resource
    private StudentBindService studentBindService;
    @Resource
    private TeachingEvaluationService teachingEvaluationService;
    @Resource
    private OpenIdService openIdService;

    private static ExecutorService evaluatePool = new MDCThreadPool(4, 4,
            0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), r -> new Thread(r, "evaluate"));

    private static final int ACCOUNT_LENGTH = 10;
    private static final String ACCOUNT_PREFIX = "20";

    @RequestMapping(value = "/bind", method = RequestMethod.GET)
    public String loginHtml(@RequestParam(value = "openid", required = false) String openid,
                            @RequestParam(value = "appid", required = false) String appid) {
        httpSession.setAttribute("openid", openid);
        httpSession.setAttribute("appid", appid);
        return "LoginWeb/Login";
    }

    /**
     * 菜单点击的绑定界面用户识别
     *
     * @param code  用户换取微信用户openid的code
     * @param state 菜单回传的状态码  这里填appid来区别公众号
     */
    @RequestMapping(value = "/bind/menu", method = RequestMethod.GET)
    public String menu(@RequestParam(value = "code") String code,
                       @RequestParam(value = "state") String state) {

        WxMpService wxMpService = WechatMpConfiguration.getMpServices().get(state);

        try {
            WxMpOAuth2AccessToken token = wxMpService.oauth2getAccessToken(code);
            httpSession.setAttribute("openid", token.getOpenId());
            httpSession.setAttribute("appid", state);
        } catch (WxErrorException e) {
            log.error("get token error", e);
        }

        return "LoginWeb/Login";
    }


    /**
     * 菜单点击的绑定界面用户识别
     *
     * @param code  用户换取微信用户openid的code
     * @param state 菜单回传的状态码  这里填appid来区别公众号
     */
    @RequestMapping(value = "/bind/evaluate", method = RequestMethod.GET)
    public String autoEvaluate(@RequestParam(value = "code") String code,
                               @RequestParam(value = "state") String state) {

        WxMpService wxMpService = WechatMpConfiguration.getMpServices().get(state);

        try {
            WxMpOAuth2AccessToken token = wxMpService.oauth2getAccessToken(code);
            httpSession.setAttribute("openid", token.getOpenId());
            httpSession.setAttribute("appid", state);
        } catch (WxErrorException e) {
            log.error("get token error", e);
        }

        return "LoginWeb/evaluate";
    }


    @RequestMapping(value = "/bind/wechat", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public WebResponse loginHtmlPost(@RequestParam("account") String account,
                                     @RequestParam("password") String password,
                                     @RequestParam(value = "appid", required = false) String appid,
                                     @RequestParam(value = "openid", required = false) String openid
    ) {
        if (appid == null) {
            appid = (String) httpSession.getAttribute("appid");
        }
        if (openid == null) {
            openid = (String) httpSession.getAttribute("openid");
        }

        log.info("student bind start account:{} password:{} appId:{} openid:{}", account, password, appid, openid);

        if (!isAccountValid(account)) {
            log.info("student getStudentInfo fail--invalid account:{}", account);
            return WebResponse.fail(ErrorCode.ACCOUNT_OR_PASSWORD_INVALID.getErrorCode(), "账号无效");
        }

        Student student;
        try {
            if (StringUtils.isEmpty(openid)) {
                student = studentBindService.studentLogin(account, password);
            } else {
                student = studentBindService.studentBind(openid, account, password, appid);
            }
            httpSession.setAttribute("account", account);
        } catch (UrpVerifyCodeException e) {
            log.info("student bind fail verify code error account:{} password:{} openid:{}", account, password,
                    openid);
            return WebResponse.fail(ErrorCode.VERIFY_CODE_ERROR.getErrorCode(), "验证码错误");
        } catch (PasswordUnCorrectException e) {
            log.info("student bind fail Password not correct account:{} password:{} openid:{}", account, password, openid);
            return WebResponse.fail(ErrorCode.ACCOUNT_OR_PASSWORD_INVALID.getErrorCode(), "账号或者密码错误");
        } catch (OpenidExistException e) {
            log.info("student bind fail openid is exist account:{} password:{}", account, password);
            return WebResponse.fail(ErrorCode.OPENID_EXIST.getErrorCode(), "该账号已经绑定");
        }

        log.info("student bind success account:{} password:{}, appId:{} openid:{}", account, password, appid, openid);
        return WebResponse.success(student);
    }


    @RequestMapping(value = "/autoEvaluate", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public WebResponse autoEvaluate(@RequestParam("account") String account,
                                    @RequestParam("password") String password,
                                    @RequestParam(value = "appid", required = false) String appid,
                                    @RequestParam(value = "openid", required = false) String openid
    ) {
        if (appid == null) {
            appid = (String) httpSession.getAttribute("appid");
        }
        if (openid == null) {
            openid = (String) httpSession.getAttribute("openid");
        }

        log.info("student evaluate start account:{} password:{} appId:{} openid:{}", account, password, appid, openid);

        if (!isAccountValid(account)) {
            log.info("student getStudentInfo fail--invalid account:{}", account);
            return WebResponse.fail(ErrorCode.ACCOUNT_OR_PASSWORD_INVALID.getErrorCode(), "账号无效");
        }

        Student student;
        try {
            student = login(account, password, appid, openid);
            return getWebResponse(student);
        } catch (UrpVerifyCodeException e) {
            log.info("student bind fail verify code error account:{} password:{} openid:{}", account, password,
                    openid);
            return WebResponse.fail(ErrorCode.VERIFY_CODE_ERROR.getErrorCode(), "验证码错误");
        } catch (PasswordUnCorrectException e) {
            log.info("student bind fail Password not correct account:{} password:{} openid:{}", account, password, openid);
            return WebResponse.fail(ErrorCode.ACCOUNT_OR_PASSWORD_INVALID.getErrorCode(), "账号或者密码错误");
        } catch (OpenidExistException e) {
            student = openIdService.getStudentByOpenId(openid, appid);
            return getWebResponse(student);
        } catch (UrpEvaluationException e) {
            // TODO 丑陋
            String appid1 = appid;
            String openid1 = openid;
            evaluatePool.submit(() -> {
                int count = 0;
                while (count < 4){
                    try {
                        teachingEvaluationService.evaluateForNotBind(Integer.parseInt(account), password, appid1, openid1);
                    }catch (Exception e1){
                        log.info("evaluate fail {}, {}, {}, {}", account, password,
                                appid1, openid1, e1);
                        count ++;
                        continue;
                    }
                    break;
                }
            });
            return WebResponse.successWithMessage("我们很快会为你完成评估，可以关闭此页面。评估完成会发信息通知你的");
        }


    }

    private WebResponse getWebResponse(Student student) {
        if(teachingEvaluationService.hasEvaluate(student.getAccount().toString())){
            return WebResponse.successWithMessage("你的账号已经评估完成啦");
        }

        if(teachingEvaluationService.isWaitingEvaluate(student.getAccount().toString())){
            return WebResponse.successWithMessage("你的账号已经在队列中啦，可以关闭此页面。评估完成会发信息通知你的");
        }

        teachingEvaluationService.addEvaluateAccount(student.getAccount().toString());
        return WebResponse.successWithMessage("我们很快会为你完成评估，可以关闭此页面。评估完成会发信息通知你的");
    }

    private Student login( String account, String password, String appid, String openid) {
        Student student;
        if (StringUtils.isEmpty(openid)) {
            student = studentBindService.studentLogin(account, password);
        } else {
            student = studentBindService.studentBind(openid, account, password, appid);
        }
        httpSession.setAttribute("account", account);

        return student;
    }


    private boolean isAccountValid(String account) {
        return !Objects.isNull(account) && account.length() == ACCOUNT_LENGTH && account.startsWith(ACCOUNT_PREFIX);
    }

}
