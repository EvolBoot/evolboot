-- QA
DROP TABLE IF EXISTS evoltb_system_qa;
CREATE TABLE evoltb_system_qa
(
    id_               bigint   NOT NULL COMMENT '主键',
    create_time_      datetime NOT NULL COMMENT '创建时间',
    last_modify_time_ datetime NOT NULL COMMENT '最后修改时间',
    version_          bigint   NOT NULL DEFAULT 0 COMMENT '乐观锁的版本号',

    locales_          text COMMENT '本地化',
    enable_           tinyint(1)        default 1 COMMENT '启用',
    sort_             int               default 0 COMMENT ' 排序',
    link_             varchar(256) COMMENT '链接',
    PRIMARY KEY (id_)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='QA';
