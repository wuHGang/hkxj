package cn.hkxj.platform.pojo.constant;

import cn.hkxj.platform.utils.ApplicationUtil;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;

/**
 * @author Yuki
 * @date 2019/6/13 20:47
 * 存储关联小程序的一些信息
 */
public enum MiniProgram {


    /**
     * 小程序课表的网页url
     */
    COURSE_PATH("pages/core/kb/kb") {
        @Override
        public WxMpKefuMessage genCard(String openid, String appId) {
            WxMpKefuMessage wxMpKefuMessage = getWxMpKefuMessage(openid);
            wxMpKefuMessage.setTitle("点我查成绩");
            if(isPro(appId)){
                wxMpKefuMessage.setThumbMediaId("qcf_h2hm7P1RL81csrh8MKad7aD7B4-ELE2316PyBmc");
            }else {
                wxMpKefuMessage.setThumbMediaId("H6QjSZxFJfx6AKCm2l6UM0usb2p54hT68hGPnNm9lxU");
            }
            return wxMpKefuMessage;
        }
    },

    /**
     * 小程序空教室
     */
    EMPTY_CLASSROOM("pages/core/empty_classroom/empty_classroom") {
        @Override
        public WxMpKefuMessage genCard(String openid, String appId) {
            WxMpKefuMessage wxMpKefuMessage = getWxMpKefuMessage(openid);
            wxMpKefuMessage.setTitle("点我查空教室");
            if(isPro(appId)){
                wxMpKefuMessage.setThumbMediaId("qcf_h2hm7P1RL81csrh8MJULS6CI73Lb_X0M4U3T");
            }else {
                wxMpKefuMessage.setThumbMediaId("H6QjSZxFJfx6AKCm2l6UM-9zSlC0OcGo7zrqzwBZ60o");
            }
            return wxMpKefuMessage;
        }
    },
    /**
     * 小程序成绩网页的url
     */
    GRADE_PATH("pages/core/cj/cj") {
        @Override
        public WxMpKefuMessage genCard(String openid, String appId) {
            WxMpKefuMessage wxMpKefuMessage = getWxMpKefuMessage(openid);
            wxMpKefuMessage.setTitle("点我看课表");
            if(isPro(appId)){
                wxMpKefuMessage.setThumbMediaId("qcf_h2hm7P1RL81csrh8MPR7jXpZUD0uYVRAZNPraw8");
            }else {
                wxMpKefuMessage.setThumbMediaId("H6QjSZxFJfx6AKCm2l6UMzF1ptnHgTmRWaohbeka1EI");
            }
            return wxMpKefuMessage;
        }
    },
    /**
     * 小程序首页
     */
    INDEX("pages/index/index") {
        @Override
        public WxMpKefuMessage genCard(String openid, String appId) {
            WxMpKefuMessage wxMpKefuMessage = getWxMpKefuMessage(openid);
            wxMpKefuMessage.setTitle("点我进入小程序");
            if(isPro(appId)){
                wxMpKefuMessage.setThumbMediaId(null);
            }else {
                wxMpKefuMessage.setThumbMediaId(null);
            }
            return wxMpKefuMessage;
        }
    };

    /**
     * 小程序的appid
     */
    public static final String APP_ID = "wx05f7264e83fa40e9";
    private static final String PRO_ID = "wx2fa77d7048b61431";
    private String value;

    boolean isPro(String appId){
        return PRO_ID.equals(appId);
    }

    public abstract WxMpKefuMessage genCard(String openid, String appId);


    public WxMpKefuMessage getWxMpKefuMessage(String openid){
        WxMpKefuMessage wxMpKefuMessage = new WxMpKefuMessage();
        wxMpKefuMessage.setMsgType(WxConsts.KefuMsgType.MINIPROGRAMPAGE);
        wxMpKefuMessage.setMiniProgramAppId(APP_ID);
        wxMpKefuMessage.setMiniProgramPagePath(getValue());
        wxMpKefuMessage.setToUser(openid);
        return wxMpKefuMessage;
    }

    MiniProgram(String value){
        this.value = value;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
