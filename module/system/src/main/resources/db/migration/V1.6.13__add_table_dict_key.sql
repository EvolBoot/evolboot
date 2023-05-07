-- 字典key
DROP TABLE IF EXISTS evol_system_dict_key;
CREATE TABLE evol_system_dict_key
(
    id_           bigint    NOT NULL COMMENT '主键',
    create_at_    timestamp NOT NULL COMMENT '创建时间',
    update_at_    timestamp NOT NULL COMMENT '最后修改时间',
    version_      bigint    NOT NULL DEFAULT 0 COMMENT '乐观锁的版本号',

    display_name_ varchar(50) COMMENT '显示名称',
    key_          varchar(50) COMMENT 'key',
    sort_         int COMMENT '排序',
    remark_       varchar(100) COMMENT '备注',
    PRIMARY KEY (id_)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='字典key';

create unique index evol_system_dict_key_key__uindex
    on evol_system_dict_key (key_);

