-- 好友申请
DROP TABLE IF EXISTS evol_im_friend_apply;
CREATE TABLE evol_im_friend_apply
(
    id_            bigint   NOT NULL COMMENT '主键',
    create_at_     datetime NOT NULL COMMENT '创建时间',
    update_at_     datetime NOT NULL COMMENT '最后修改时间',
    version_       bigint   NOT NULL DEFAULT 0 COMMENT '乐观锁的版本号',

    owner_user_id_ bigint COMMENT '被申请人',
    apply_user_id_ bigint COMMENT '申请人',
    apply_reason_  varchar(50) COMMENT '申请原因',
    status_        tinyint COMMENT '状态',
    expire_at_     datetime COMMENT '过期时间',
    handle_at_     datetime COMMENT '处理时间',
    PRIMARY KEY (id_)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='好友申请';

create index evol_im_friend_apply__index
    on evol_im_friend_apply (owner_user_id_, apply_user_id_, status_);