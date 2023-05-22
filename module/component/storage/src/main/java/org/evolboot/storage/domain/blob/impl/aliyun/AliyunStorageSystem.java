
package org.evolboot.storage.domain.blob.impl.aliyun;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.Setter;
import org.evolboot.core.util.PathUtil;
import org.evolboot.storage.domain.blob.impl.AbstractStorageSystem;
import org.evolboot.storage.domain.blob.impl.StorageBlob;
import org.evolboot.storage.domain.blob.entity.StorageType;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.io.IOException;
import java.util.Objects;

public class AliyunStorageSystem extends AbstractStorageSystem implements InitializingBean, DisposableBean {

    @Setter
    private String accessKeyId;

    @Setter
    private String accessKeySecret;

    @Setter
    private String endpoint;

    @Setter
    private String bucketName;

    private OSS client;

    public AliyunStorageSystem(String baseUrl) {
        super(baseUrl);
    }

    @Override
    public void afterPropertiesSet() {
        this.client = new OSSClientBuilder().build(this.endpoint, this.accessKeyId, this.accessKeySecret);
    }


    @Override
    public void destroy() {
        if (Objects.nonNull(this.client)) {
            this.client.shutdown();
        }
    }

    @Override
    protected String storeBlobToUrl(StorageBlob blob, String path) throws IOException {
        this.client.putObject(this.bucketName, PathUtil.removePrefixSeparator(path), blob.getFile());
        return path;
    }

    @Override
    protected StorageType getStorageType() {
        return StorageType.ALIYUN;
    }
}
