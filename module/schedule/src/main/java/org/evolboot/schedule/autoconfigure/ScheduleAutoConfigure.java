package org.evolboot.schedule.autoconfigure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

/**
 * @author evol
 */
@EnableScheduling
@Component
@Slf4j
public class ScheduleAutoConfigure {


    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler threadPoolScheduler = new ThreadPoolTaskScheduler();
        /**
         * 线程数
         */
        int poolSize = 10;
        threadPoolScheduler.setPoolSize(poolSize);
        threadPoolScheduler.setThreadNamePrefix("scheduled-task-");
        threadPoolScheduler.setRemoveOnCancelPolicy(true);
        log.info("配置Spring定时任务");
        return threadPoolScheduler;
    }

}
