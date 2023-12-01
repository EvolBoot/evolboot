
package org.evolboot.storage.domain.blob.adapter.qcloud;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;
import lombok.Setter;
import org.evolboot.core.util.PathUtil;
import org.evolboot.storage.domain.blob.adapter.AbstractStorageSystem;
import org.evolboot.storage.domain.blob.adapter.StorageBlob;
import org.evolboot.storage.domain.blob.entity.StorageType;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.io.IOException;
import java.util.Objects;

public class QCloudStorageSystem extends AbstractStorageSystem implements InitializingBean, DisposableBean {

    @Setter
    private String secretId;

    @Setter
    private String secretKey;

    @Setter
    private String region;

    @Setter
    private String bucketName;

    private COSClient client;

    public QCloudStorageSystem(String baseUrl) {
        super(baseUrl);
    }

    @Override
    public void afterPropertiesSet() {
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        Region region = new Region(this.region);
        ClientConfig clientConfig = new ClientConfig(region);
        this.client = new COSClient(cred, clientConfig);
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
        return StorageType.QCLOUD;
    }
}
