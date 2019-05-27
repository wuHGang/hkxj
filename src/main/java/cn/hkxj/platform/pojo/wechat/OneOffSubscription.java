package cn.hkxj.platform.pojo.wechat;

import java.util.Objects;

/**
 * @author Yuki
 * @date 2018/12/6 21:03
 */
public class OneOffSubscription {

    private final String touser;
    private final String template_id;
    private final String url;
    private final Miniprogram miniprogram;
    private final String scene;
    private final String title;
    private final Data data;

    private OneOffSubscription(Builder builder){
        this.touser = builder.touser;
        this.template_id = builder.template_id;
        this.url = builder.url;
        this.scene = builder.scene;
        this.title = builder.title;
        this.data = builder.data;
        this.miniprogram = builder.miniprogram;
    }

    public static class Builder{
        private final String touser;
        private final String template_id;
        private final String scene;
        private final String title;

        private  Data data;
        private String url;
        private Miniprogram miniprogram;

        public Builder(String touser, String scene, String title, String template_id){
            this.touser = touser;
            this.scene = scene;
            this.title = title;
            this.template_id = template_id;
        }

        public Builder url(String url){
            this.url = url;
            return this;
        }

        public Builder miniprogram(String app_id, String pagepath){
            this.miniprogram = new Miniprogram(app_id, pagepath);
            return this;
        }

        public Builder data(String value, String color){
            this.data = new Data(new Content(value, color));
            return this;
        }

        public Builder data(String value){
            this.data = new Data(new Content(value, "black"));
            return this;
        }

        public OneOffSubscription build(){
            OneOffSubscription oneOffSubscription =  new OneOffSubscription(this);
            if(Objects.isNull(oneOffSubscription.data)){
                throw new IllegalArgumentException("data cannot be null");
            }
            return oneOffSubscription;
        }
    }
}

class Data{
    private final Content content;

    public Data(Content content){
        this.content = content;
    }
}

class Content{
    private final String value;
    private final String color;

    public Content(String value, String color){
        this.value = value;
        this.color = color;
    }
}

class Miniprogram{
    private final String app_id;
    private final String pagepath;

    public Miniprogram(String app_id, String pagepath){
        this.app_id = app_id;
        this.pagepath = pagepath;
    }
}
