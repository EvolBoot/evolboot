-- 群成员
DROP TABLE IF EXISTS evol_im_group_member;
CREATE TABLE evol_im_group_member
(
    id_                   bigint   NOT NULL COMMENT '主键',
    create_at_            datetime NOT NULL COMMENT '创建时间',
    update_at_            datetime NOT NULL COMMENT '最后修改时间',
    version_              bigint   NOT NULL DEFAULT 0 COMMENT '乐观锁的版本号',

    group_id_             datetime COMMENT '群ID',
    member_user_id_       datetime COMMENT '成员用户ID',
    role_                 tinyint COMMENT '角色',
    status_               tinyint COMMENT '状态',
    forbid_talk_deadline_ datetime COMMENT '禁言截止时间',
    PRIMARY KEY (id_)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='群成员';