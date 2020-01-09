package cn.hkxj.platform.spider;

import cn.hkxj.platform.exceptions.UrpException;
import cn.hkxj.platform.pojo.constant.RedisKeys;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
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
    @Value("${useProxy}")
    private String useProxy;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public List<Proxy> select(URI uri) {

        List<Proxy> list = new ArrayList<>();

        if(BooleanUtils.toBoolean(useProxy)){
            if (usePayProxy()) {
                ProxyData proxyData = getProxyData();
                list.add(new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(proxyData.ip, proxyData.port)));
            }else {
                list.add(new Proxy(Proxy.Type.SOCKS, new InetSocketAddress("49.234.214.204", 8888)));
            }

        }else {
            list.add(Proxy.NO_PROXY);
        }
        return list;

    }

    @Override
    public void connectFailed(URI uri, SocketAddress sa, IOException ioe) {

        if((ioe instanceof SocketTimeoutException || ioe instanceof SocketException) && proxyCache.canUpdate()){
            log.info("update proxy");
            updateProxy();
        }else if(!(ioe instanceof SocketTimeoutException)){
            log.error("poxy connectFailed", ioe);
        }

    }

    public boolean usePayProxy(){
        String name = RedisKeys.PROXY_SELECT_SWITCH.getName();
        return BooleanUtils.toBoolean(stringRedisTemplate.opsForValue().get(name));
    }


    public ProxyData getProxyData() {
        ProxyData proxyData;

        synchronized (UrpSpiderProxySelector.class) {
            if (proxyCache == null || proxyCache.isExpire()) {
                proxyData = updateProxy();
            } else {
                proxyData = proxyCache.proxyData;
            }
        }

        return proxyData;

    }

    private synchronized ProxyData updateProxy(){

        ProxyData proxyData = getProxyDataFromRemote();
        proxyCache = new ProxyCache(proxyData);
        return proxyData;
    }

    @Retryable(value = RuntimeException.class, maxAttempts = 2, backoff =@Backoff(value = 2000,
            multiplier = 2))
    private ProxyData getProxyDataFromRemote() {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> map = new HashMap<>();

        map.put("appKey", "529923302983356416");
        map.put("appSecret", appSecret);
        map.put("wt", "json");
        map.put("method", "s5");
        Response response = restTemplate.getForObject(server, Response.class, map);
        if(response.getCode() == 200){
            return response.getData().stream().findFirst().orElseThrow(RuntimeException::new);
        }else {
            log.error("get proxy error {}", response);
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
            return System.currentTimeMillis() - createDate.getTime() > 1000 * 60 * (proxyData.during-0.5);
        }

        boolean canUpdate() {
            return System.currentTimeMillis() - createDate.getTime() > 15 * 1000;
        }
    }
}
