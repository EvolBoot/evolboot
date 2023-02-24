-- 第三方代收订单
DROP TABLE IF EXISTS evoltb_pay_receipt_order;
CREATE TABLE evoltb_pay_receipt_order
(
    id_                              varchar(50) NOT NULL COMMENT '主键',
    create_time_                     datetime    NOT NULL COMMENT '创建时间',
    last_modify_time_                datetime    NOT NULL COMMENT '最后修改时间',
    version_                         bigint      NOT NULL DEFAULT 0 COMMENT '乐观锁的版本号',

    internal_order_id_               varchar(50) COMMENT '内部订单号',
    product_name_                    varchar(50) COMMENT '产品名称',
    payee_name_                      varchar(50) COMMENT '支付人名称',
    payee_phone_                     varchar(50) COMMENT '支付人手机号',
    payee_email_                     varchar(100) COMMENT '支付人邮箱',
    callback_url_                    varchar(256) COMMENT '前端回调地址',
    pay_gateway_account_id_          bigint COMMENT '支付网关的账户ID',
    pay_gateway_                     varchar(50) COMMENT '支付网关',
    pay_amount_                      decimal(19, 2) COMMENT '支付金额',
    status_                          varchar(50) COMMENT '支付状态',

    request_result_foreign_order_id_ varchar(100) COMMENT '请求返回时的第三方订单ID',
    request_result_pay_url_          varchar(256) COMMENT '请求返回的URL',
    request_result_request_text_     text COMMENT '请求返回的全文信息',

    notify_result_foreign_order_id_  varchar(50) COMMENT '通知返回的第三方订单ID',
    notify_result_myself_order_id_   varchar(50) COMMENT '通知返回的我方订单ID',
    notify_result_status_            varchar(50) COMMENT '通知返回的状态',
    notify_result_notify_text_       text COMMENT '通知返回的全文信息',
    notify_result_pay_amount_        decimal(19, 2) COMMENT '通知返回的支付金额',
    notify_result_arrival_amount_    decimal(19, 2) COMMENT '通知返回的到账金额',
    notify_result_poundage_          decimal(19, 2) COMMENT '通知返回的手续费',
    PRIMARY KEY (id_)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='第三方代收订单';
