package cn.hkxj.platform.pojo.wechat;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * @author Yuki
 * @date 2018/12/6 21:03
 * 与微信一次性订阅信息回复的json格式对应的类
 * {
 * “touser”:”OPENID”,
 * “template_id”:”TEMPLATE_ID”,
 * “url”:”URL”,
 * “miniprogram”:{
 * “appid”:“xiaochengxuappid12345”,
 * “pagepath”:“index?foo=bar”
 * },
 * “scene”:”SCENE”,
 * “title”:”TITLE”,
 * “data”:{
 * “content”:{
 * “value”:”VALUE”,
 * “color”:”COLOR”
 * }
 * }
 * }
 */
public class OneOffSubscription {

    /**
     * 填接收消息的用户openid
     */
    private final String touser;
    /**
     * 订阅消息模板ID
     */
    private final String templateId;
    /**
     * 可选，点击消息跳转的链接，需要有ICP备案
     */
    private final String url;
    /**
     * 可选，对应的小程序。内部包含appid和pagePath两个属性
     * 所需跳转到的小程序appid（该小程序appid必须与发模板消息的公众号是绑定关联关系，并且小程序要求是已发布的）
     * 所需跳转到小程序的具体页面路径，支持带参数,（示例index?foo=bar）
     */
    private final Miniprogram miniprogram;
    /**
     * 订阅的场景值
     */
    private final String scene;
    /**
     * 消息标题，15字以内
     */
    private final String title;
    /**
     * 消息正文，value为消息内容文本（200字以内），没有固定格式，可用\n换行，color为整段消息内容的字体颜色（目前仅支持整段消息为一种颜色）
     */
    private final Data data;

    private OneOffSubscription(Builder builder) {
        this.touser = builder.touser;
        this.templateId = builder.templateId;
        this.url = builder.url;
        this.scene = builder.scene;
        this.title = builder.title;
        this.data = builder.data;
        this.miniprogram = builder.miniprogram;
    }

    public String getTouser() {
        return touser;
    }

    public String getTemplateId() {
        return templateId;
    }

    public String getUrl() {
        return url;
    }

    public Miniprogram getMiniprogram() {
        return miniprogram;
    }

    public String getScene() {
        return scene;
    }

    public String getTitle() {
        return title;
    }

    public Data getData() {
        return data;
    }

    public static class Builder {
        private String touser;
        private String templateId;
        private String scene;
        private String title;
        private Data data;
        private String url;
        private Miniprogram miniprogram;

        public Builder() {

        }

        public Builder(OneOffSubscription oneOffSubscription) {
            this.touser = oneOffSubscription.touser;
            this.scene = oneOffSubscription.scene;
            this.title = oneOffSubscription.title;
            this.templateId = oneOffSubscription.templateId;
            this.url = oneOffSubscription.url;
            this.data = oneOffSubscription.data;
            this.miniprogram = oneOffSubscription.miniprogram;
        }

        public Builder touser(String touser) {
            if (touser == null) {
                throw new IllegalArgumentException("touser == null");
            }
            this.touser = touser;
            return this;
        }

        public Builder scene(String scene) {
            if (scene == null) {
                throw new IllegalArgumentException("scene == null");
            }
            this.scene = scene;
            return this;
        }

        public Builder templateId(String templateId) {
            if (templateId == null) {
                throw new IllegalArgumentException("templateId == null");
            }
            this.templateId = templateId;
            return this;
        }

        public Builder title(String title) {
            if (title == null) {
                throw new IllegalArgumentException("title == null");
            }
            if(title.length() > 15){
                throw new IllegalArgumentException("limit length to 15");
            }
            this.title = title;
            return this;
        }

        public Builder url(String url) {
            if (url == null) {
                throw new IllegalArgumentException("url == null");
            }
            this.url = url;
            return this;
        }

        public Builder miniprogram(String appid, String pagepath) {
            if (appid == null) {
                throw new IllegalArgumentException("appid == null");
            }
            if (pagepath == null) {
                throw new IllegalArgumentException("pagepath == null");
            }
            this.miniprogram = new Miniprogram(appid, pagepath);
            return this;
        }

        public Builder data(String value, String color) {
            if (value == null) {
                throw new IllegalArgumentException("value == null");
            }
            if (color == null) {
                throw new IllegalArgumentException("color == null");
            }
            if(value.length() > 200){
                throw new IllegalArgumentException("limit length to 200");
            }
            this.data = new Data(new Content(value, color));
            return this;
        }

        public Builder data(String value) {
            if (value == null) {
                throw new IllegalArgumentException("value == null");
            }
            if(value.length() > 200){
                throw new IllegalArgumentException("limit length to 200");
            }
            this.data = new Data(new Content(value, "black"));
            return this;
        }

        public OneOffSubscription build() {
            OneOffSubscription oneOffSubscription = new OneOffSubscription(this);
            if (StringUtils.isEmpty(touser)) {
                throw new IllegalArgumentException("touser == null or touser is empty ");
            }
            if (StringUtils.isEmpty(scene)) {
                throw new IllegalArgumentException("scene == null or scene is empty");
            }
            if (StringUtils.isEmpty(title)) {
                throw new IllegalArgumentException("title == null or title is empty");
            }
            if (StringUtils.isEmpty(templateId)) {
                throw new IllegalArgumentException("templateId == null or templateId is empty");
            }
            if (Objects.isNull(oneOffSubscription.data)) {
                throw new IllegalArgumentException("data == null");
            }
            return new OneOffSubscription(this);
        }
    }
}

class Data {
    private Content content;

    Data(Content content) {
        this.content = content;
    }
}

class Content {
    private String value;
    private String color;

    Content(String value, String color) {
        this.value = value;
        this.color = color;
    }
}

class Miniprogram {
    private String appid;
    private String pagepath;

    Miniprogram(String appid, String pagepath) {
        this.appid = appid;
        this.pagepath = pagepath;
    }
}


