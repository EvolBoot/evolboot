-- 文件存储
DROP TABLE IF EXISTS evoltb_storage_blob;
CREATE TABLE evoltb_storage_blob
(
    id_            bigint       NOT NULL COMMENT '主键',
    create_time_   datetime     NOT NULL COMMENT '创建时间',
    path_          varchar(256) NOT NULL COMMENT '本地路径',
    url_           varchar(256) NOT NULL COMMENT 'URL',
    name_          varchar(256) NOT NULL COMMENT '文件名',
    original_name_ varchar(256) NOT NULL COMMENT '原始文件名',
    storage_type_  varchar(256) NOT NULL COMMENT '存储类型',
    extension_     varchar(50)  NOT NULL COMMENT '扩展名',
    size_          long COMMENT '大小',
    md5_           varchar(256) COMMENT 'md5',
    crc32_         varchar(256) COMMENT 'crc32',
    type_          varchar(20) COMMENT '类型',
    owner_user_id_ bigint COMMENT '文件上传人',


    PRIMARY KEY (id_)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='evoltb_user';
