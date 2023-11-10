-- 字典Value
DROP TABLE IF EXISTS evoltb_system_dict_value;
CREATE TABLE evoltb_system_dict_value
(
    id_           bigint    NOT NULL COMMENT '主键',
    create_at_    timestamp NOT NULL COMMENT '创建时间',
    update_at_    timestamp NOT NULL COMMENT '最后修改时间',
    version_      bigint    NOT NULL DEFAULT 0 COMMENT '乐观锁的版本号',

    dict_key_id_  varchar(100) COMMENT '备注',
    display_name_ varchar(50) COMMENT '显示名称',
    value_        varchar(50) COMMENT '字段值',
    sort_         int COMMENT '排序',
    remark_       varchar(100) COMMENT '备注',
    PRIMARY KEY (id_)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='字典Value';
