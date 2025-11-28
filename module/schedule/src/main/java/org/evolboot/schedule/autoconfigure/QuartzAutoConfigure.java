package org.evolboot.schedule.autoconfigure;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.schedule.ScheduleConstant;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Quartz 调度器配置
 * <p>
 * 职责：仅处理必须通过代码设置的配置（DataSource 引用）
 * 其他所有配置都在 application.yml 中管理
 * <p>
 * 依赖 Flyway 先执行数据库迁移，确保 Quartz 表已创建
 *
 * @author evol
 */
@Configuration
@ConditionalOnProperty(name = ScheduleConstant.SCHEDULE_QUARTZ_ENABLE, havingValue = "true")
@AutoConfigureAfter({FlywayAutoConfiguration.class})
@Slf4j
public class QuartzAutoConfigure {

    private final QuartzProperties quartzProperties;

    public QuartzAutoConfigure(QuartzProperties quartzProperties) {
        this.quartzProperties = quartzProperties;
    }

    /**
     * 配置 Quartz SchedulerFactoryBean
     * <p>
     * 核心职责：
     * 1. 注入 Spring 管理的 DataSource（这是必须通过代码完成的）
     * 2. 读取 application.yml 中的所有 Quartz 配置
     * 3. 设置应用上下文引用（用于 Job 中注入 Spring Bean）
     *
     * @param dataSource Spring 管理的数据源
     * @return SchedulerFactoryBean
     */
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(DataSource dataSource) {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();

        // 1. 设置数据源（必须通过代码设置）
        factory.setDataSource(dataSource);

        // 2. 设置应用上下文，允许 Job 中使用 @Autowired 注入 Spring Bean
        factory.setApplicationContextSchedulerContextKey("applicationContext");

        // 3. 读取 application.yml 中的所有配置
        Properties props = new Properties();
        props.putAll(quartzProperties.getProperties());
        factory.setQuartzProperties(props);

        // 4. 读取 Spring Boot Quartz 的标准配置
        factory.setOverwriteExistingJobs(quartzProperties.isOverwriteExistingJobs());
        factory.setAutoStartup(quartzProperties.isAutoStartup());
        if (quartzProperties.getStartupDelay() != null) {
            factory.setStartupDelay((int) quartzProperties.getStartupDelay().getSeconds());
        }

        log.info("Quartz 调度器配置完成：单实例运行，线程数={}", props.getProperty("org.quartz.threadPool.threadCount", "10"));

        return factory;
    }
}
