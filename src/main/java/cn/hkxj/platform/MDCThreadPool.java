package cn.hkxj.platform;

import org.slf4j.MDC;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author junrong.chen
 */
public class MDCThreadPool extends ThreadPoolExecutor {
    public MDCThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    @Override
    public void execute(Runnable command) {
        final Map<String, String> contextMap = MDC.getCopyOfContextMap();
        super.execute(() -> {
            if(contextMap == null){
                MDC.clear();
            }else {
                MDC.setContextMap(contextMap);
            }

            try {
                command.run();
            }finally {
                MDC.clear();
            }
        });
    }
}
