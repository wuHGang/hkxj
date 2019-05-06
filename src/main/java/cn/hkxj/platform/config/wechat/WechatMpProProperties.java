package cn.hkxj.platform.config.wechat;

import com.google.common.base.Objects;
import lombok.Data;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;

/**
 * wechat mp properties
 *
 * @author Binary Wang(https://github.com/binarywang)
 */
@Data
@ConfigurationProperties(prefix = "wechat.mp.pro")
public class WechatMpProProperties{

	@Value("${wechat.mp.pro.appid}")
	private String appId;

	@Value("${wechat.mp.pro.secret}")
	private String secret;

	@Value("${wechat.mp.pro.token}")
	private String token;

	@Value("${wechat.mp.pro.aesKey}")
	private String aesKey;

	@Value("${wechat.mp.pro.templateId}")
	private String templateId;

	private WxMpInMemoryConfigStorage wxMpInMemoryConfigStorage;

	@PostConstruct
	public void init(){
		wxMpInMemoryConfigStorage = new WxMpInMemoryConfigStorage();
		wxMpInMemoryConfigStorage.setAppId(appId);
		wxMpInMemoryConfigStorage.setSecret(secret);
		wxMpInMemoryConfigStorage.setToken(token);
		wxMpInMemoryConfigStorage.setAesKey(aesKey);
		wxMpInMemoryConfigStorage.setTemplateId(templateId);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		WechatMpProProperties that = (WechatMpProProperties) o;
		return Objects.equal(appId, that.appId) &&
				Objects.equal(secret, that.secret) &&
				Objects.equal(token, that.token) &&
				Objects.equal(aesKey, that.aesKey) &&
				Objects.equal(wxMpInMemoryConfigStorage, that.wxMpInMemoryConfigStorage);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(super.hashCode(), appId, secret, token, aesKey, wxMpInMemoryConfigStorage);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}
}
