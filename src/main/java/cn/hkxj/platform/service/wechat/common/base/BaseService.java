package cn.hkxj.platform.service.wechat.common.base;

import cn.hkxj.platform.pojo.Wechatuser;
import cn.hkxj.platform.service.spider.AppSpider;

import java.io.IOException;

/**
 * @author yukiill
 * @date 2018/7/13 23:14
 */
public class BaseService {

	/**
	 * 在数据库没有相应数据时，获取一个爬虫对象以便进行数据爬取
	 *
	 * @param wechatuser 必须包含学号和密码
	 * @return
	 * @throws IOException
	 */
	protected AppSpider getAppsSider(Wechatuser wechatuser) throws IOException {
		AppSpider appSpider = new AppSpider();
		appSpider.setAccount(wechatuser.getAccount());
		appSpider.setPasswd(wechatuser.getPasswd() + "murp");
		String token = appSpider.getToken();
		appSpider.setToken(token);
		return appSpider;
	}

}
