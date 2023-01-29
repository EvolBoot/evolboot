-- 权限
DROP TABLE IF EXISTS evol_identity_permission;
CREATE TABLE evol_identity_permission
(
    id_               bigint       NOT NULL COMMENT '主键',
    create_time_      datetime     NOT NULL COMMENT '创建时间',
    last_modify_time_ datetime     NOT NULL COMMENT '最后修改时间',
    version_          bigint       NOT NULL DEFAULT 0 COMMENT '乐观锁的版本号',

    title_            varchar(256) null comment '权限名称',
    perm_             varchar(256) null comment '权限',
    path_             varchar(256) null comment 'URL',
    type_             varchar(64)  null comment '类型',
    sort_             int                   default 0 null comment '排序',
    icon_             varchar(64)  null comment '前端菜单图标',
    remark_           text         null comment '备注',
    PRIMARY KEY (id_)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='evol_identity_permission';
