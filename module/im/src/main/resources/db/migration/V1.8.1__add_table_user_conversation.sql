-- 用户会话
DROP TABLE IF EXISTS evol_im_user_conversation;
CREATE TABLE evol_im_user_conversation
(
    id_                bigint   NOT NULL COMMENT '主键',
    create_at_         datetime NOT NULL COMMENT '创建时间',
    update_at_         datetime NOT NULL COMMENT '最后修改时间',
    version_           bigint   NOT NULL DEFAULT 0 COMMENT '乐观锁的版本号',

    owner_user_id_     bigint COMMENT '拥有者',
    conversation_id_   bigint COMMENT '会话ID',
    conversation_type_ int COMMENT '类型',
    remark_            varchar(100) COMMENT '备注',
    PRIMARY KEY (id_)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户会话';
