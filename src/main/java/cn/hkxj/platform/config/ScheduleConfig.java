package cn.hkxj.platform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * @author Yuki
 * @date 2019/6/7 14:50
 * 这个配置是因为如果如果没有给@Schedule对应的设置。
 * 所有的定时任务都是在一个线程中执行。如果前一个定时任务没有完成，会影响下一个任务的执行。
 * 所以设置一个线程池的同时配合@Async注释让每一个定时任务都运行在单独的一个线程中
 */
@Configuration
@EnableAsync
public class ScheduleConfig {

    @Bean
    public TaskScheduler taskScheduler(){
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(10);
        return taskScheduler;
    }
}
