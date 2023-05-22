package org.evolboot.storage.domain.blob.impl;

import lombok.Builder;
import lombok.Getter;
import org.evolboot.storage.domain.blob.entity.StorageType;

/**
 * 存储文件接口
 */
public interface StorageSystem {

    Response storeBlob(StorageBlob blob);

    @Builder
    @Getter
    class Response {
        String url;
        String path;
        String name;
        StorageType storageType;
    }

}
