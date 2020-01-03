package cn.hkxj.platform.spider;

import cn.hkxj.platform.exceptions.UrpException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
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
    public synchronized List<Proxy> select(URI uri) {

        ProxyData proxyData = getProxyData();

        List<Proxy> list = new ArrayList<>();
        list.add(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyData.ip, proxyData.port)));

        return list;
    }

    @Override
    public void connectFailed(URI uri, SocketAddress sa, IOException ioe) {


        if(ioe instanceof SocketTimeoutException){
            log.info("update proxy");
            ProxyData proxyData = getProxyDataFromRemote();
            proxyCache = new ProxyCache(proxyData);
        }else {
            log.error("poxy connectFailed", ioe);
        }

    }


    public ProxyData getProxyData() {
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

    @Retryable(value = UrpException.class, maxAttempts = 2, backoff =@Backoff(value = 500,
            multiplier = 2))
    private ProxyData getProxyDataFromRemote() {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> map = new HashMap<>();

        map.put("appKey", "529923302983356416");
        map.put("appSecret", appSecret);
        map.put("wt", "json");
        map.put("method", "http");
        Response response = restTemplate.getForObject(server, Response.class, map);
        if(response.getCode() == 200){
            return response.getData().stream().findFirst().orElseThrow(RuntimeException::new);
        }
        throw new RuntimeException();

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
