-- APP更新
DROP TABLE IF EXISTS evol_system_app_upgrade;
CREATE TABLE evol_system_app_upgrade
(
    id_               bigint   NOT NULL COMMENT '主键',
    create_time_      datetime NOT NULL COMMENT '创建时间',
    last_modify_time_ datetime NOT NULL COMMENT '最后修改时间',
    version_          bigint   NOT NULL DEFAULT 0 COMMENT '乐观锁的版本号',

    title_            varchar(128) COMMENT 'APP版本号',
    app_version_      varchar(256) COMMENT 'APP版本号',
    client_type_      varchar(50) COMMENT '客户端类型',
    upgrade_info_     varchar(4096) COMMENT '升级描述',
    upgrade_date_     varchar(50) COMMENT '升级时间',
    download_url_     varchar(256) COMMENT '下载地址',
    locales_          text COMMENT '更新标题',
    show_dialog_      tinyint(1)        default 1 comment '显示弹窗',
    PRIMARY KEY (id_)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='APP更新';
