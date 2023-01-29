package org.evolboot.storage.domain.blob;

/**
 * 文件存储位置
 *
 * @author evol
 * 
 */
public enum StorageType {
    LOCAL, // 本地，和本应用使用的同一个IP 端口
    ALIYUN, // 阿里云
    QINIU,  // 七牛云
    QCLOUD,  // 腾讯云
    STATIC_SERVER,     // 静态服务器，比如Nginx
    MINIO;

}