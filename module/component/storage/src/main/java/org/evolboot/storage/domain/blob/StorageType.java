package org.evolboot.storage.domain.blob;

import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;

/**
 * 文件存储位置
 *
 * @author evol
 * 
 */
@Getter
@AllArgsConstructor
public enum StorageType {
    LOCAL(0), // 本地，和本应用使用的同一个IP 端口
    ALIYUN(1), // 阿里云
    QINIU(2),  // 七牛云
    QCLOUD(3),  // 腾讯云
    STATIC_SERVER(4),     // 静态服务器，比如Nginx
    MINIO(5);

    private final Integer value;

    private static final Map<Integer, StorageType> VALUES = Maps.newHashMapWithExpectedSize(StorageType.values().length);

    static {
        Arrays.stream(StorageType.values()).forEach(e -> {
            VALUES.put(e.getValue(), e);
        });
    }


    public static StorageType convertTo(Integer value) {
        return VALUES.get(value);
    }


}