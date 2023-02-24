DROP TABLE IF EXISTS evoltb_email_captcha;
CREATE TABLE evoltb_email_captcha
(
    token_              varchar(50) NOT NULL COMMENT '主键',
    create_time_        datetime    NOT NULL COMMENT '创建时间',
    email_              varchar(20) NOT NULL COMMENT '邮箱',
    message_tag_        varchar(20) NOT NULL COMMENT '消息Tag',
    code_               varchar(20) NOT NULL COMMENT '验证码',
    expires_            bigint      NOT NULL COMMENT '有效期',
    interval_           bigint      NOT NULL COMMENT '间隔时间',
    limit_verify_count_ int        default 0 COMMENT '验证次数',
    verify_result_      tinyint(1) default 0 comment '验证结果',
    ip_                 varchar(50) COMMENT '获取IP',
    internal_code_      varchar(50) COMMENT '内部码',

    PRIMARY KEY (token_)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='邮箱验证码';
