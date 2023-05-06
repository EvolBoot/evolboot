-- 聊天记录
DROP TABLE IF EXISTS evol_im_chat_record;
CREATE TABLE evol_im_chat_record
(
    id_                           bigint    NOT NULL COMMENT '主键',
    create_at_                    timestamp NOT NULL COMMENT '创建时间',
    update_at_                    timestamp NOT NULL COMMENT '最后修改时间',
    version_                      bigint    NOT NULL DEFAULT 0 COMMENT '乐观锁的版本号',

    client_msg_id_                varchar(32) COMMENT '客户端消息ID',
    sender_id_                    bigint COMMENT '发送者ID',
    sender_role_                  tinyint COMMENT '发送者角色',
    device_type_                  tinyint COMMENT '设备类型',
    conversation_type_            tinyint COMMENT '会话类型',
    conversation_id_              bigint COMMENT '会话ID',
    message_content_              varchar(3000) COMMENT '聊天内容',
    message_type_                 tinyint COMMENT '消息类型',
    status_                       tinyint COMMENT '状态',
    revoke_message_content_value_ varchar(3000) COMMENT '撤回的消息备注',
    PRIMARY KEY (id_)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='聊天记录';
