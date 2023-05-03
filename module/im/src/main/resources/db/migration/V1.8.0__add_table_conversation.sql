-- 会话
DROP TABLE IF EXISTS evol_im_conversation;
CREATE TABLE evol_im_conversation
(
    id_                 bigint   NOT NULL COMMENT '主键',
    create_at_          datetime NOT NULL COMMENT '创建时间',
    update_at_          datetime NOT NULL COMMENT '最后修改时间',
    version_            bigint   NOT NULL DEFAULT 0 COMMENT '乐观锁的版本号',

    type_               int COMMENT '类型',
    relation_id_        varchar(32) COMMENT '关联的ID',
    quantity_of_people_ int COMMENT '人数',
    PRIMARY KEY (id_)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='会话';
