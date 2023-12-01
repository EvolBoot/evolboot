package org.evolboot.storage.domain.blob.adapter.minio;

import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import io.minio.errors.MinioException;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.exception.DomainException;
import org.evolboot.storage.domain.blob.adapter.AbstractStorageSystem;
import org.evolboot.storage.domain.blob.adapter.StorageBlob;
import org.evolboot.storage.domain.blob.entity.StorageType;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author evol
 */
@Slf4j
public class MinioStorageSystem extends AbstractStorageSystem implements InitializingBean, DisposableBean {

    private MinioClient client;
    private String bucket;
    private String endpoint;
    private String accessKey;
    private String secretAccessKey;
    private String baseUrl;

    public MinioStorageSystem(String bucket, String endpoint, String accessKey, String secretAccessKey, String baseUrl) {
        super(baseUrl);
        this.baseUrl = baseUrl;
        this.bucket = bucket;
        this.endpoint = endpoint;
        this.accessKey = accessKey;
        this.secretAccessKey = secretAccessKey;
    }

    @Override
    protected String storeBlobToUrl(StorageBlob blob, String path) throws IOException {
        try {
            client.uploadObject(UploadObjectArgs
                    .builder()
                    .object(path)
                    .bucket(bucket)
                    .filename(blob.getFile().getAbsolutePath())
                    .build());
        } catch (NoSuchAlgorithmException | InvalidKeyException | MinioException e) {
            log.error("Minio:上传错误:", e);
            throw new DomainException("Error uploading the file");
        }
        return path;
    }

    @Override
    protected StorageType getStorageType() {
        return StorageType.MINIO;
    }

    @Override
    public void destroy() throws Exception {
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("初始化:Minio");
        client = MinioClient
                .builder()
                .endpoint(endpoint)
                .credentials(accessKey, secretAccessKey)
                .build();
    }
}
