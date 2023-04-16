-- Banner
DROP TABLE IF EXISTS evoltb_system_banner;
CREATE TABLE evoltb_system_banner
(
    id_        bigint   NOT NULL COMMENT '主键',
    create_at_ datetime NOT NULL COMMENT '创建时间',
    update_at_ datetime NOT NULL COMMENT '最后修改时间',
    version_   bigint   NOT NULL DEFAULT 0 COMMENT '乐观锁的版本号',

    sort_      int COMMENT '排序',
    show_      tinyint(1) default 1 COMMENT '是否显示',
    locales_   text COMMENT '多语言',
    PRIMARY KEY (id_)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='Banner';
