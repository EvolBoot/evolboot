package org.evolboot.storage.autoconfigure;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.storage.domain.blob.adapter.StorageSystem;
import org.evolboot.storage.domain.blob.adapter.aliyun.AliyunStorageSystem;
import org.evolboot.storage.domain.blob.adapter.local.LocalStorageSystem;
import org.evolboot.storage.domain.blob.adapter.minio.MinioStorageSystem;
import org.evolboot.storage.domain.blob.adapter.qcloud.QCloudStorageSystem;
import org.evolboot.storage.domain.blob.adapter.qiniu.QiniuStorageSystem;
import org.evolboot.storage.domain.blob.adapter.staticserver.StaticServerStorageSystem;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.EncodedResourceResolver;

@Configuration
@EnableConfigurationProperties(StorageProperties.class)
@Slf4j
public class StorageAutoConfiguration {


    @Bean
    @ConditionalOnMissingBean(StorageSystem.class)
    @ConditionalOnClass(LocalStorageSystem.class)
    @ConditionalOnProperty(prefix = StorageProperties.CONFIGURATION_PREFIX, name = "type", havingValue = "local")
    public StorageSystem localStorageSystem(StorageProperties properties) {
        StorageProperties.Local local = properties.getLocal();
        log.info("文件存储类型: 本地存储, 配置: {}", local);
        return new LocalStorageSystem(local.getDirectory(), local.getPrefix(), local.getBaseUrl());
    }


    /**
     * 和本应用使用的同一个端口和IP, 需要将 prefix 对应的请求转到这个目录下
     */
    @Configuration
    @Slf4j
    @ConditionalOnProperty(prefix = StorageProperties.CONFIGURATION_PREFIX, name = "type", havingValue = "local")
    public static class LocalStorageSystemAutoConfiguration implements WebMvcConfigurer {

        private final StorageProperties properties;

        public LocalStorageSystemAutoConfiguration(StorageProperties properties) {
            this.properties = properties;
        }

        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            log.info("本地静态资源访问路径: {}", properties.getLocal().getPrefix());
            log.info("本地静态资源上传路径: {}", properties.getLocal().getDirectory());
            registry.addResourceHandler(properties.getLocal().getPrefix() + "/**")
                    .addResourceLocations("file:" + properties.getLocal().getDirectory() + "/")
                    .resourceChain(true)
                    .addResolver(new EncodedResourceResolver());

        }
    }


    @Bean
    @ConditionalOnMissingBean(StorageSystem.class)
    @ConditionalOnClass(StaticServerStorageSystem.class)
    @ConditionalOnProperty(prefix = StorageProperties.CONFIGURATION_PREFIX, name = "type", havingValue = "static-server")
    public StaticServerStorageSystem staticServerStorageSystem(StorageProperties properties) {
        StorageProperties.StaticServer staticServer = properties.getStaticServer();
        log.info("文件存储类型: 静态服务器, 配置: {}", staticServer);
        return new StaticServerStorageSystem(staticServer.getDirectory(), staticServer.getBaseUrl());
    }


    @Bean
    @ConditionalOnMissingBean(StorageSystem.class)
    @ConditionalOnClass(AliyunStorageSystem.class)
    @ConditionalOnProperty(prefix = StorageProperties.CONFIGURATION_PREFIX, name = "type", havingValue = "aliyun")
    public StorageSystem aliyunStorageSystem(StorageProperties properties) {
        StorageProperties.Aliyun aliyun = properties.getAliyun();
        log.info("文件存储类型: 阿里云, 配置: {}", aliyun);
        AliyunStorageSystem ass = new AliyunStorageSystem(aliyun.getBaseUrl());
        ass.setAccessKeyId(aliyun.getAccessKeyId());
        ass.setAccessKeySecret(aliyun.getAccessKeySecret());
        ass.setEndpoint(aliyun.getEndpoint());
        ass.setBucketName(aliyun.getBucketName());
        return ass;
    }

    @Bean
    @ConditionalOnMissingBean(StorageSystem.class)
    @ConditionalOnClass(QiniuStorageSystem.class)
    @ConditionalOnProperty(prefix = StorageProperties.CONFIGURATION_PREFIX, name = "type", havingValue = "qiniu")
    public StorageSystem qiniuStorageSystem(StorageProperties properties) {
        StorageProperties.Qiniu qiniu = properties.getQiniu();
        log.info("文件存储类型: 七牛云, 配置: {}", qiniu);
        QiniuStorageSystem qss = new QiniuStorageSystem(qiniu.getBaseUrl());
        qss.setAccessKey(qiniu.getAccessKey());
        qss.setSecretKey(qiniu.getSecretKey());
        qss.setBucket(qiniu.getBucket());
        qss.setRegion(qiniu.getRegion());
        return qss;
    }

    @Bean
    @ConditionalOnMissingBean(StorageSystem.class)
    @ConditionalOnClass(QCloudStorageSystem.class)
    @ConditionalOnProperty(prefix = StorageProperties.CONFIGURATION_PREFIX, name = "type", havingValue = "qcloud")
    public StorageSystem qCloudStorageSystem(StorageProperties properties) {
        StorageProperties.QCloud qCloud = properties.getQCloud();
        log.info("文件存储类型: 腾讯云, 配置: {}", qCloud);
        QCloudStorageSystem qc = new QCloudStorageSystem(qCloud.getBaseUrl());
        qc.setRegion(qCloud.getRegion());
        qc.setBucketName(qCloud.getBucketName());
        qc.setSecretId(qCloud.getSecretId());
        qc.setSecretKey(qCloud.getSecretKey());
        return qc;
    }

    @Bean
    @ConditionalOnMissingBean(StorageSystem.class)
    @ConditionalOnClass(QCloudStorageSystem.class)
    @ConditionalOnProperty(prefix = StorageProperties.CONFIGURATION_PREFIX, name = "type", havingValue = "minio")
    public StorageSystem minioStorageSystem(StorageProperties properties) {
        StorageProperties.Minio minio = properties.getMinio();
        log.info("文件存储类型: Minio, 配置: {}", minio);
        return new MinioStorageSystem(
                minio.getBucket(),
                minio.getEndpoint(),
                minio.getAccessKey(),
                minio.getSecretAccessKey(),
                minio.getBaseUrl()
        );
    }


}
