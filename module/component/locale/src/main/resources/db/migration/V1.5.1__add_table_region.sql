-- 国家
DROP TABLE IF EXISTS evol_locale_region;
CREATE TABLE evol_locale_region
(
    id_               bigint   NOT NULL COMMENT '主键',
    create_time_      datetime NOT NULL COMMENT '创建时间',
    last_modify_time_ datetime NOT NULL COMMENT '最后修改时间',
    version_          bigint   NOT NULL DEFAULT 0 COMMENT '乐观锁的版本号',

    sort_             int COMMENT '排序',
    name_             varchar(100) comment '国家名称',
    short_name_       varchar(30) comment '缩写名称',
    mobile_prefix_    varchar(30) comment '手机号前缀',
    flag_             varchar(256) comment '国旗链接',
    remark_           varchar(50) COMMENT '备注',
    enable_           tinyint(1)        default 1 comment '启用状态',

    PRIMARY KEY (id_)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='evol_locale_region';
