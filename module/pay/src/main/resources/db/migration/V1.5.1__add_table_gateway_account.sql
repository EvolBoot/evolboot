-- 支付网关账户
DROP TABLE IF EXISTS evoltb_pay_gateway_account;
CREATE TABLE evoltb_pay_gateway_account
(
    id_              bigint   NOT NULL COMMENT '主键',
    create_at_       datetime NOT NULL COMMENT '创建时间',
    update_at_       datetime NOT NULL COMMENT '最后修改时间',
    version_         bigint   NOT NULL DEFAULT 0 COMMENT '乐观锁的版本号',

    logo_            varchar(128) COMMENT 'Logo',
    locales_         varchar(1024) COMMENT '本地化',
    merchant_id_     varchar(100) COMMENT '商户ID',
    appid_           varchar(100) COMMENT 'appid',
    secret_key_      varchar(100) COMMENT '秘钥',
    minimum_receipt_ decimal(20, 3) COMMENT '最小代收',
    maximum_receipt_ decimal(20, 3) COMMENT '最大代收',
    enable_          tinyint(1) default 1 COMMENT '启用',
    pay_gateway_     varchar(50) COMMENT '对应的网关符号',
    wallet_id_       varchar(50) null comment '钱包ID',
    sort_            int               default 0 COMMENT '排序',
    alias_           varchar(50) COMMENT '别名',
    PRIMARY KEY (id_)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='支付网关账户';

create unique index evoltb_pay_gateway_account_alias__uindex
    on evoltb_pay_gateway_account (alias_);

