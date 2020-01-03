package cn.hkxj.platform.spider;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.*;
import java.util.*;


@Slf4j
@Component("urpSpiderProxySelector")
public class UrpSpiderProxySelector extends ProxySelector {

    private static final String server = "http://api.xiaoxiangdaili.com/ip/get?appKey={appKey}&appSecret={appSecret" +
            "}&wt={wt}&method={method}";

    private static ProxyCache proxyCache;

    @Value("${appSecret}")
    private String appSecret;

    @Override
    public List<Proxy> select(URI uri) {

        ProxyData proxyData = getProxyData();

        List<Proxy> list = new ArrayList<>();
        list.add(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyData.ip, proxyData.port)));

        return list;
    }

    @Override
    public void connectFailed(URI uri, SocketAddress sa, IOException ioe) {
        log.error("poxy connectFailed", ioe);
        getProxyData();
    }


    public ProxyData getProxyData(){
        ProxyData proxyData;

        synchronized (UrpSpiderProxySelector.class) {
            if (proxyCache == null || proxyCache.isExpire()) {
                proxyData = getProxyDataFromRemote();
                proxyCache = new ProxyCache(proxyData);
            } else {
                proxyData = proxyCache.proxyData;
            }
        }

        return proxyData;

    }

    private ProxyData getProxyDataFromRemote() {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> map = new HashMap<>();

        map.put("appKey", "529923302983356416");
        map.put("appSecret", appSecret);
        map.put("wt", "json");
        map.put("method", "http");
        Response response = restTemplate.getForObject(server, Response.class, map);
        return response.getData().stream().findFirst().orElseThrow(RuntimeException::new);

    }


    @Data
    private static class Response {
        private Integer code;
        private String success;
        private List<ProxyData> data;
        private String msg;
    }

    @Data
    private static class ProxyData {
        private String ip;
        private Integer port;
        private String realIp;
        private Integer during;
    }

    @Data
    private static class ProxyCache {
        private ProxyData proxyData;
        private Date createDate;

        ProxyCache(ProxyData proxyData) {
            this.proxyData = proxyData;
            this.createDate = new Date();
        }

        boolean isExpire() {
            return System.currentTimeMillis() - createDate.getTime() > 1000 * 60 * proxyData.during;
        }
    }
}
