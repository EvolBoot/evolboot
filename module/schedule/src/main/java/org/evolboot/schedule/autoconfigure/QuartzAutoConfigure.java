package org.evolboot.schedule.autoconfigure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Quartz 调度器配置
 *
 * @author evol
 */
@Configuration
@ConditionalOnProperty(name = "evolpn.schedule.quartz.enable", havingValue = "true")
@Slf4j
public class QuartzAutoConfigure {

    private final DataSource dataSource;

    public QuartzAutoConfigure(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setDataSource(dataSource);
        factory.setOverwriteExistingJobs(true);
        factory.setStartupDelay(10);
        factory.setApplicationContextSchedulerContextKey("applicationContext");
        factory.setQuartzProperties(quartzProperties());

        log.info("配置 Quartz 调度器：数据库模式，单实例运行");
        return factory;
    }

    /**
     * Quartz 属性配置
     */
    private Properties quartzProperties() {
        Properties props = new Properties();

        // 调度器基本配置
        props.setProperty("org.quartz.scheduler.instanceName", "evolpn-scheduler");
        props.setProperty("org.quartz.scheduler.instanceId", "AUTO");
        props.setProperty("org.quartz.scheduler.skipUpdateCheck", "true");

        // 线程池配置
        props.setProperty("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
        props.setProperty("org.quartz.threadPool.threadCount", "10");
        props.setProperty("org.quartz.threadPool.threadPriority", "5");
        props.setProperty("org.quartz.threadPool.threadsInheritContextClassLoaderOfInitializingThread", "true");

        // JobStore 配置（数据库持久化）
        props.setProperty("org.quartz.jobStore.class", "org.quartz.impl.jdbcjobstore.JobStoreTX");
        props.setProperty("org.quartz.jobStore.driverDelegateClass", "org.quartz.impl.jdbcjobstore.StdJDBCDelegate");
        props.setProperty("org.quartz.jobStore.tablePrefix", "QRTZ_");
        props.setProperty("org.quartz.jobStore.useProperties", "false");

        // 集群配置（单实例模式）
        props.setProperty("org.quartz.jobStore.isClustered", "false");

        // Misfire 处理
        props.setProperty("org.quartz.jobStore.misfireThreshold", "60000");

        log.info("Quartz 配置：线程池大小=10，数据库模式，非集群");
        return props;
    }
}
