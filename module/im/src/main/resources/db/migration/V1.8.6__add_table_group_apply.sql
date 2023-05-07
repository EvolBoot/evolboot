-- 群申请
DROP TABLE IF EXISTS evol_im_group_apply;
CREATE TABLE evol_im_group_apply
(
    id_             bigint    NOT NULL COMMENT '主键',
    create_at_      timestamp NOT NULL COMMENT '创建时间',
    update_at_      timestamp NOT NULL COMMENT '最后修改时间',
    version_        bigint    NOT NULL DEFAULT 0 COMMENT '乐观锁的版本号',

    group_id_       bigint COMMENT '群ID',
    apply_user_id_  bigint COMMENT '申请人ID',
    apply_reason_   json COMMENT '申请加群原因',
    status_         tinyint COMMENT '状态',
    expire_at_      timestamp COMMENT '时间时间',
    handle_user_id_ bigint COMMENT '处理人ID',
    handle_at_      timestamp COMMENT '处理时间',
    PRIMARY KEY (id_)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='群申请';
