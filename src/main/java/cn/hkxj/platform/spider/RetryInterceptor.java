package cn.hkxj.platform.spider;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.annotations.EverythingIsNonNull;

import java.io.IOException;

/**
 * @author junrong.chen
 * @date 2019/7/17
 */
@Slf4j
@EverythingIsNonNull
public class RetryInterceptor implements Interceptor {
    /**
     * 最大重试次数
     */
    private int maxRetry;

    RetryInterceptor(int maxRetry) {
        this.maxRetry = maxRetry;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        int retryNum = 0;
        while (retryNum < maxRetry) {
            retryNum++;
            try {
                Response response = chain.proceed(chain.request());

                if(!response.isSuccessful()&& !response.isRedirect()){
                    log.debug("urp url {} request retry  time {}, cause {}, code {}", chain.request().url().toString(), retryNum, response.message(), response.code());
                }else {
                    return response;
                }
            }catch (IOException e){
                log.debug("urp url {} request retry  time {}, cause {}", chain.request().url().toString(), retryNum, e.getMessage());
            }

        }
        return chain.proceed(chain.request());
    }
}
