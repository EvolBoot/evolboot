
package org.evolboot.storage.domain.blob.qiniu;

import org.evolboot.core.util.Assert;
import org.evolboot.core.util.PathUtil;
import org.evolboot.storage.domain.blob.AbstractStorageSystem;
import org.evolboot.storage.domain.blob.StorageBlob;
import org.evolboot.storage.domain.blob.StorageBoldException;
import org.evolboot.storage.domain.blob.StorageType;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import lombok.Setter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.ReflectionUtils;

import java.io.IOException;
import java.lang.reflect.Method;

public class QiniuStorageSystem extends AbstractStorageSystem implements InitializingBean {

    @Setter
    private String accessKey;

    @Setter
    private String secretKey;

    @Setter
    private String region;

    @Setter
    private String bucket;

    private Auth auth;

    private UploadManager manager;

    public QiniuStorageSystem(String baseUrl) {
        super(baseUrl);
    }

    @Override
    protected String storeBlobToUrl(StorageBlob blob, String path) throws IOException {
        String token = auth.uploadToken(this.bucket);
        com.qiniu.http.Response response = manager.put(blob.getFile(), PathUtil.removePrefixSeparator(path), token);
        if (response.isServerError()) {
            throw new StorageBoldException(response.error);
        }
        return path;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.auth = Auth.create(this.accessKey, this.secretKey);
        this.manager = new UploadManager(new Configuration(this.getRegionInstance()));
    }

    @Override
    protected StorageType getStorageType() {
        return StorageType.QINIU;
    }

    private Region getRegionInstance() {
        Method regionMethod = ReflectionUtils.findMethod(Region.class, this.region);
        Assert.notNull(regionMethod, String.format("Region(%s) does not exist.", this.region));
        return (Region) ReflectionUtils.invokeMethod(regionMethod, null);
    }

}
