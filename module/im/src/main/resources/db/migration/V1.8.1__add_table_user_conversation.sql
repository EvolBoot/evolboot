-- 用户会话
DROP TABLE IF EXISTS evol_im_user_conversation;
CREATE TABLE evol_im_user_conversation
(
    id_                 bigint    NOT NULL COMMENT '主键',
    create_at_          timestamp NOT NULL COMMENT '创建时间',
    update_at_          timestamp NOT NULL COMMENT '最后修改时间',
    version_            bigint    NOT NULL DEFAULT 0 COMMENT '乐观锁的版本号',

    owner_user_id_      bigint COMMENT '拥有者',
    conversation_id_    bigint COMMENT '会话ID',
    conversation_type_  tinyint COMMENT '类型:0 群聊,1 私聊',
    state_              tinyint COMMENT '状态',
    forbid_talk_causes_ smallint COMMENT '禁言原因',
    group_id_           bigint COMMENT '群ID,类型为0时有值',
    friend_user_id_     bigint COMMENT '朋友ID,用户为1时有',
    remark_             varchar(100) COMMENT '备注',
    PRIMARY KEY (id_)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户会话';

-- 唯一索引
create unique index evol_im_user_conversation_owner_user_id__conversation_id__uindex
    on evol_im_user_conversation (owner_user_id_, conversation_id_);

