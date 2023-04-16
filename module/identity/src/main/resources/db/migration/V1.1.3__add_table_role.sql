-- 角色
DROP TABLE IF EXISTS evoltb_identity_role;
CREATE TABLE evoltb_identity_role
(
    id_        bigint       NOT NULL COMMENT '主键',
    create_at_ datetime     NOT NULL COMMENT '创建时间',
    update_at_ datetime     NOT NULL COMMENT '最后修改时间',
    version_   bigint       NOT NULL DEFAULT 0 COMMENT '乐观锁的版本号',

    role_name_ varchar(256) NOT NULL COMMENT '角色名称',
    remark_    text COMMENT '备注',

    PRIMARY KEY (id_)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='角色';