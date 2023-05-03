-- 群组
DROP TABLE IF EXISTS evol_im_group;
CREATE TABLE evol_im_group
(
    id_              bigint   NOT NULL COMMENT '主键',
    create_at_       datetime NOT NULL COMMENT '创建时间',
    update_at_       datetime NOT NULL COMMENT '最后修改时间',
    version_         bigint   NOT NULL DEFAULT 0 COMMENT '乐观锁的版本号',

    owner_user_id_   bigint COMMENT '群主ID',
    name_            varchar(30) COMMENT '群名称',
    avatar_          varchar(128) COMMENT '群头像',
    description_     varchar(512) COMMENT '群描述',
    conversation_id_ bigint COMMENT '群对应的会话ID',
    status_          tinyint COMMENT '群状态',
    notification_    varchar(512) COMMENT '群通知',
    limit_           smallint COMMENT '限制人数',
    remark_          varchar(100) COMMENT '群备注',
    PRIMARY KEY (id_)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='群组';
