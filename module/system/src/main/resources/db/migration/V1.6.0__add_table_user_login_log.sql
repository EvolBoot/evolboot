-- 用户登录日志
DROP TABLE IF EXISTS evoltb_system_user_login_log;
CREATE TABLE evoltb_system_user_login_log
(
    id_                bigint   NOT NULL COMMENT '主键',
    create_time_       datetime NOT NULL COMMENT '创建时间',
    last_modify_time_  datetime NOT NULL COMMENT '最后修改时间',
    version_           bigint   NOT NULL DEFAULT 0 COMMENT '乐观锁的版本号',

    user_id_           bigint   NOT NULL COMMENT 'user_id_',
    login_token_       varchar(100)      default '' comment '登录Token',
    physical_location_ varchar(100)      default '' comment '登录位置',
    login_ip_          varchar(100)      default '' comment '登录IP',

    PRIMARY KEY (id_)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户登录日志';

