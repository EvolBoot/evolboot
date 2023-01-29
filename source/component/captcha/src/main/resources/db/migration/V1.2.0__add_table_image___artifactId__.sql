DROP TABLE IF EXISTS evol_image_captcha;
CREATE TABLE evol_image_captcha
(
    token_       varchar(50) NOT NULL COMMENT '主键',
    create_time_ datetime    NOT NULL COMMENT '创建时间',
    code_        varchar(20) NOT NULL COMMENT '验证码',
    ip_          varchar(50) NOT NULL COMMENT 'IP',
    expires_     bigint      NOT NULL COMMENT '有效期',
    PRIMARY KEY (token_)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='图片验证码';
