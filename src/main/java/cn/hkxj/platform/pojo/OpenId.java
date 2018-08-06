package cn.hkxj.platform.pojo;


/**
 * openid表里带有
 * openId和account
 */


public class OpenId {

	private String openId;
	private Integer account;


	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Integer getAccount() {
		return account;
	}

	public void setAccount(Integer account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "OpenId{" +
				"openId='" + openId + '\'' +
				", account=" + account +
				'}';
	}


}
