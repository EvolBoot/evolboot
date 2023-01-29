package org.evolboot;

import org.evolboot.core.data.jpa.querydsl.QuerydslJpaRepositoryFactoryBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Slf4j
@ComponentScan(
        nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class)
@EnableJpaRepositories(repositoryFactoryBeanClass = QuerydslJpaRepositoryFactoryBean.class)
public class EntryTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(EntryTestApplication.class, args);
        log.info("[ -------------------------------------- ]");
        log.info("[ ---------------测试启动---------------- ]");
        log.info("[ -------------------------------------- ]");
    }

}
