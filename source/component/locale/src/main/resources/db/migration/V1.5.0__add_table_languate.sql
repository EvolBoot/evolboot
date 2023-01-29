-- 多语言
DROP TABLE IF EXISTS evol_locale_language;
CREATE TABLE evol_locale_language
(
    id_               bigint   NOT NULL COMMENT '主键',
    create_time_      datetime NOT NULL COMMENT '创建时间',
    last_modify_time_ datetime NOT NULL COMMENT '最后修改时间',
    version_          bigint   NOT NULL DEFAULT 0 COMMENT '乐观锁的版本号',

    sort_             int COMMENT '排序',
    language_         varchar(50) COMMENT '语言',
    remark_           varchar(50) COMMENT '备注',
    enable_           tinyint(1)        default 1 comment '启用状态',

    PRIMARY KEY (id_)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='evol_locale_language';
