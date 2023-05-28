package org.evolboot;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.annotation.ApiClient;
import org.evolboot.core.data.jpa.querydsl.QuerydslJpaRepositoryFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Slf4j
@EnableCaching
@ComponentScan(
        nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class,
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {ApiClient.class})})
@EnableJpaRepositories(repositoryFactoryBeanClass = QuerydslJpaRepositoryFactoryBean.class)
public class EntryAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(EntryAdminApplication.class, args);
        log.info("[ -------------------------------------- ]");
        log.info("[ ---------------启动完成---------------- ]");
        log.info("[ -------------------------------------- ]");
    }


}
