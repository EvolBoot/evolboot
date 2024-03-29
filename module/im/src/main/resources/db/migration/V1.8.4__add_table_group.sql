-- 群组
DROP TABLE IF EXISTS evoltb_im_group;
CREATE TABLE evoltb_im_group
(
    id_                   bigint    NOT NULL COMMENT '主键',
    create_at_            timestamp NOT NULL COMMENT '创建时间',
    update_at_            timestamp NOT NULL COMMENT '最后修改时间',
    version_              bigint    NOT NULL DEFAULT 0 COMMENT '乐观锁的版本号',

    owner_user_id_        bigint COMMENT '群主ID',
    name_                 varchar(30) COMMENT '群名称',
    avatar_               varchar(128) COMMENT '群头像',
    description_          varchar(512) COMMENT '群描述',
    conversation_id_      bigint COMMENT '群对应的会话ID',
    apply_state_          tinyint COMMENT '群申请状态:0 可自由加入,1 需要申请加入, 2 禁止申请加入',
    type_                 tinyint COMMENT '群类型:0 普通群',
    forbid_talk_scope_    tinyint COMMENT '禁言范围:0 不禁言,1 全员禁言,2 仅普通会员',
    notification_         varchar(512) COMMENT '群通知',
    limit_                smallint COMMENT '限制人数',
    quantity_of_member_   smallint COMMENT '会话数量',
    forbid_talk_deadline_ timestamp COMMENT '禁言截止时间',
    remark_               varchar(100) COMMENT '群备注',
    PRIMARY KEY (id_)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='群组';
