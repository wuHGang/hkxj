package cn.hkxj.platform.config.wechat;

import com.google.common.collect.Maps;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * wechat mp properties
 *
 * @author Binary Wang(https://github.com/binarywang)
 */
@Data
@ConfigurationProperties(prefix = "wechat.mp")
public class WechatMpProperties {

	private List<WxMpConfig> configs;

	@Value("#{'${wechat.mp.appid}'.split(',')}")
	private List<String> appids;

	@Value("#{'${wechat.mp.secret}'.split(',')}")
	private List<String> secrets;

	@Value("#{'${wechat.mp.token}'.split(',')}")
	private List<String> tokens;

	@Value("#{'${wechat.mp.aesKey}'.split(',')}")
	private List<String> aesKeys;

	@Data
	public static class WxMpConfig{

		private String appId;

		private String secret;

		private String token;

		private String aesKey;
	}

	@PostConstruct
	public void init(){
		configs = new ArrayList<>();
		int size = appids.size();
		for(int i = 0; i < size; i++){
			WxMpConfig wxMpConfig = new WxMpConfig();
			wxMpConfig.setAppId(appids.get(i));
			wxMpConfig.setSecret(secrets.get(i));
			wxMpConfig.setToken(tokens.get(i));
			wxMpConfig.setAesKey(i >= aesKeys.size() ? "" : aesKeys.get(i));
			configs.add(wxMpConfig);
		}
	}




//	/**
//	 * 设置微信公众号的appid
//	 */
//	@Value("${wechat.appid}")
//	private String appId;
//
//	/**
//	 * 设置微信公众号的app secret
//	 */
//	@Value("${wechat.appSecret}")
//	private String secret;
//
//	/**
//	 * 设置微信公众号的token
//	 */
//	@Value("${wechat.token}")
//	private String token;
//
//	/**
//	 * 设置微信公众号的EncodingAESKey
//	 */
//	private String aesKey;
//
//	public String getAppId() {
//		return this.appId;
//	}
//
//	public void setAppId(String appId) {
//		this.appId = appId;
//	}
//
//	public String getSecret() {
//		return this.secret;
//	}
//
//	public void setSecret(String secret) {
//		this.secret = secret;
//	}
//
//	public String getToken() {
//		return this.token;
//	}
//
//	public void setToken(String token) {
//		this.token = token;
//	}
//
//	public String getAesKey() {
//		return this.aesKey;
//	}
//
//	public void setAesKey(String aesKey) {
//		this.aesKey = aesKey;
//	}
//
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}
}
