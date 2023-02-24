-- 账号
DROP TABLE IF EXISTS evoltb_identity_user;
CREATE TABLE evoltb_identity_user
(
    id_                 bigint   NOT NULL COMMENT '主键',
    create_time_        datetime NOT NULL COMMENT '创建时间',
    last_modify_time_   datetime NOT NULL COMMENT '最后修改时间',
    version_            bigint   NOT NULL DEFAULT 0 COMMENT '乐观锁的版本号',
    del_status_         varchar(20)       DEFAULT 'ACTIVE' COMMENT '删除状态，ACTIVE 正常 , ARCHIVE 删除',

    username_           varchar(256) COMMENT '用户名',
    nickname_           varchar(128)      default '' comment '昵称',
    email_              varchar(128) COMMENT '邮箱',
    mobile_             varchar(50) COMMENT '手机号',
    mobile_prefix_      varchar(10) COMMENT '手机号前缀',
    avatar_             varchar(256) COMMENT '头像',
    password_           varchar(256) COMMENT '密码',
    gender_             varchar(50)       default 'UNKNOWN' COMMENT '性别',
    user_identity_      varchar(1024) COMMENT '身份',
    identity_symbol_    int COMMENT '身份描述符(用来搜索)',
    status_             varchar(50) comment '用户状态',
    user_type_          varchar(50) comment '用户类型',
    inviter_user_id_    bigint comment '上级ID(邀请人ID)',
    register_ip_        varchar(100) comment '注册IP',
    login_ip_           varchar(100) comment '登录IP',
    last_login_time_    datetime comment '最后登录时间',
    google_auth_secret_ varchar(20) comment '谷歌验证秘钥',
    enable_google_auth_ tinyint(1)        default 0 comment '启用谷歌验证',
    security_password_  varchar(256) COMMENT '安全密码',
    remark_             varchar(100) COMMENT '备注',


    PRIMARY KEY (id_)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='evoltb_identity_user';

create unique index evoltb_identity_user_email__unique_index on evoltb_identity_user (email_);

create unique index evoltb_identity_user_username__unique_index on evoltb_identity_user (username_);

create unique index evoltb_identity_user_mobile__unique_index on evoltb_identity_user (mobile_);
