package org.evolboot.storage.domain.blob.adapter;

import cn.hutool.core.date.DateUtil;
import org.evolboot.core.util.PathUtil;
import org.evolboot.storage.domain.blob.StorageBoldException;
import org.evolboot.storage.domain.blob.entity.StorageType;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**
 * @author evol
 */
public abstract class AbstractStorageSystem implements StorageSystem {

    private final String baseUrl;

    public AbstractStorageSystem(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public Response storeBlob(StorageBlob blob) {
        try {
            String name = UUID.randomUUID() + "." + blob.getExtension();
            String path = DateUtil.format(new Date(), "yyyyMMdd");
            path = PathUtil.concat(path, name);
            String fullUrl = concatAccessUrl(storeBlobToUrl(blob, path));
            return Response
                    .builder()
                    .url(fullUrl)
                    .path(path)
                    .name(name)
                    .storageType(getStorageType())
                    .build();
        } catch (IOException e) {
            throw new StorageBoldException(e.getMessage());
        }
    }


    /**
     * @param blob 文件
     * @param path 路径
     * @return
     * @throws IOException
     */
    protected abstract String storeBlobToUrl(StorageBlob blob, String path) throws IOException;

    protected abstract StorageType getStorageType();

    protected String concatAccessUrl(String path) {
        return baseUrl + path;
    }


}
