-- 好友申请
DROP TABLE IF EXISTS evoltb_im_friend_apply;
CREATE TABLE evoltb_im_friend_apply
(
    id_              bigint    NOT NULL COMMENT '主键',
    create_at_       timestamp NOT NULL COMMENT '创建时间',
    update_at_       timestamp NOT NULL COMMENT '最后修改时间',
    version_         bigint    NOT NULL DEFAULT 0 COMMENT '乐观锁的版本号',

    to_user_id_      bigint COMMENT '被申请人',
    from_user_id_    bigint COMMENT '申请人',
    conversation_id_ bigint COMMENT '会话ID',
    apply_reason_    json COMMENT '申请原因',
    state_           tinyint COMMENT '状态',
    expire_at_       timestamp COMMENT '过期时间',
    handle_at_       timestamp COMMENT '处理时间',
    PRIMARY KEY (id_)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='好友申请';

create index evoltb_im_friend_apply__index
    on evoltb_im_friend_apply (to_user_id_, from_user_id_, state_);