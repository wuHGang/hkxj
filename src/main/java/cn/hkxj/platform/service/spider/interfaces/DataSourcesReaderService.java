package cn.hkxj.platform.service.spider.interfaces;

import java.io.IOException;

public interface DataSourcesReaderService {
	String read(String url) throws IOException;
}
