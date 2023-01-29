package org.evolboot.storage.domain.blob;

import lombok.Builder;
import lombok.Getter;

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
