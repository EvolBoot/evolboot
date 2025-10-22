-- 重命名 代收订单表字段：notify_result_receipt_order_id_ -> notify_result_payin_order_id_
ALTER TABLE evoltb_pay_payin_order
    CHANGE notify_result_receipt_order_id_ notify_result_payin_order_id_ varchar(50) COMMENT '通知返回的我方订单ID';

