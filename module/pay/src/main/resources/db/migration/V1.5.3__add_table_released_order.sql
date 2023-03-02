-- 代付订单
DROP TABLE IF EXISTS evoltb_pay_released_order;
CREATE TABLE evoltb_pay_released_order
(
    id_                              varchar(50) NOT NULL COMMENT '订单号主键',
    create_time_                     datetime    NOT NULL COMMENT '创建时间',
    last_modify_time_                datetime    NOT NULL COMMENT '最后修改时间',
    version_                         bigint      NOT NULL DEFAULT 0 COMMENT '乐观锁的版本号',

    internal_order_id_               varchar(100) COMMENT '内部订单号',
    amount_                          decimal(19, 2) COMMENT '金额',
    payee_name_                      varchar(50) COMMENT '客户姓名',
    payee_phone_                     varchar(50) COMMENT '客户手机号',
    payee_email_                     varchar(100) COMMENT '客户邮箱',
    bank_code_                       varchar(100) COMMENT '银行代码',
    bank_no_                         varchar(100) COMMENT '银行卡号',
    pay_gateway_account_id_          bigint COMMENT '网关ID',
    pay_gateway_                     varchar(100) COMMENT '网关',
    poundage_                        varchar(100) COMMENT '手续费',
    foreign_order_id_                varchar(100) COMMENT '第三方ID',
    request_text_                    text COMMENT '请求出去',
    request_result_text_             text COMMENT '请求返回',
    request_result_foreign_order_id_ varchar(100) COMMENT '请求返回第三方ID',
    request_result_foreign_status_   varchar(100) COMMENT '请求返回状态',

    notify_result_foreign_order_id_  varchar(100) COMMENT '通知返回第三方ID',
    notify_result_released_order_id_ varchar(100) COMMENT '通知返回下发ID',
    notify_result_foreign_status_    varchar(100) COMMENT '通知返回状态',
    notify_result_text_              text COMMENT '通知返回',
    notify_result_amount_            decimal(19, 2) COMMENT '通知返回金额',
    notify_result_poundage_          varchar(100) COMMENT '通知返回手续费',
    status_                          varchar(50) COMMENT '状态',
    PRIMARY KEY (id_)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='代付订单';
