
package org.evolboot.storage.autoconfigure;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import static org.evolboot.storage.autoconfigure.StorageProperties.CONFIGURATION_PREFIX;

@Getter
@ToString
@Setter
@ConfigurationProperties(prefix = CONFIGURATION_PREFIX)
public class StorageProperties {

    public final static String CONFIGURATION_PREFIX = "evol.storage";


    @NestedConfigurationProperty
    private Local local = new Local();

    @NestedConfigurationProperty
    private StaticServer staticServer = new StaticServer();

    @NestedConfigurationProperty
    private Aliyun aliyun = new Aliyun();

    @NestedConfigurationProperty
    private Qiniu qiniu = new Qiniu();

    @NestedConfigurationProperty
    private QCloud qCloud = new QCloud();


    @NestedConfigurationProperty
    private Minio minio = new Minio();


    @NestedConfigurationProperty
    private Output output = new Output();


    @Getter
    @Setter
    @ToString
    static class Local {
        private String baseUrl;
        private String prefix;
        private String directory;
    }

    @Getter
    @Setter
    @ToString
    static class StaticServer {
        private String baseUrl;
        private String directory;
    }

    @Getter
    @Setter
    @ToString
    static class Aliyun {
        private String baseUrl;
        private String accessKeyId;
        private String accessKeySecret;
        private String endpoint;
        private String bucketName;
    }

    @Getter
    @Setter
    @ToString
    static class Qiniu {
        private String baseUrl;
        private String accessKey;
        private String secretKey;
        private String region;
        private String bucket;
    }

    @Getter
    @Setter
    @ToString
    static class QCloud {
        private String baseUrl;
        private String secretId;
        private String secretKey;
        private String region;
        private String bucketName;
    }

    @Getter
    @Setter
    static class Output {
        private String path = "/{yyyy}/{MM}/{dd}";
        private String filename = "{uuid}.{ext}";
    }


    @Getter
    @Setter
    @ToString
    static class Minio {
        private String baseUrl;
        private String bucket;
        private String endpoint;
        private String accessKey;
        private String secretAccessKey;
    }
}
