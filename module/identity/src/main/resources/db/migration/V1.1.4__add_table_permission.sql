-- 权限
DROP TABLE IF EXISTS evoltb_identity_permission;
CREATE TABLE evoltb_identity_permission
(
    id_            bigint       NOT NULL COMMENT '主键',
    create_at_     timestamp    NOT NULL COMMENT '创建时间',
    update_at_     timestamp    NOT NULL COMMENT '最后修改时间',
    version_       bigint       NOT NULL DEFAULT 0 COMMENT '乐观锁的版本号',

    parent_id_     bigint       null comment '父级ID',
    parent_ids_    json         null comment '父级ID链',
    name_          varchar(128) null comment '权限名称',
    component_     varchar(128) null comment '组件路径',
    path_          varchar(256) null comment 'path_',
    type_          tinyint      null comment '类型',
    sort_          int                   default 0 null comment '排序',
    title_         varchar(64)  null comment '标题',
    icon_          varchar(64)  null comment '前端菜单图标',
    is_hide_       tinyint(1)            default 0 comment '是否隐藏',
    is_keep_alive_ tinyint(1)            default 0 comment '是否保持会话',
    is_affix_      tinyint(1)            default 0 comment '是否固定',
    is_link_       tinyint(1)            default 0 comment '是否链接',
    link_          varchar(256) null comment '对外链接',
    is_iframe_     tinyint(1)            default 0 comment '前端菜单图标',
    perm_          varchar(256) null comment '权限',

    PRIMARY KEY (id_)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='evoltb_identity_permission';
