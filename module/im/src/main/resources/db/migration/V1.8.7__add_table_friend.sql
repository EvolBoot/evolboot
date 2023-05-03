-- 好友关系
DROP TABLE IF EXISTS evol_im_friend;
CREATE TABLE evol_im_friend
(
    id_              bigint   NOT NULL COMMENT '主键',
    create_at_       datetime NOT NULL COMMENT '创建时间',
    update_at_       datetime NOT NULL COMMENT '最后修改时间',
    version_         bigint   NOT NULL DEFAULT 0 COMMENT '乐观锁的版本号',

    owner_user_id_   bigint COMMENT '所有者ID',
    friend_user_id_  bigint COMMENT '好友ID',
    conversation_id_ bigint COMMENT '会话ID',
    status_          tinyint COMMENT '状态',
    nickname_remark_ varchar(50) COMMENT '昵称备注',
    PRIMARY KEY (id_)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='好友关系';
