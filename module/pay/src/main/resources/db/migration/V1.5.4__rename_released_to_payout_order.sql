-- 重命名 代付订单表 与相关字段
-- 注意：不同数据库版本对 RENAME/CHANGE 语法支持不同，请按实际环境校验

-- 表重命名
RENAME TABLE evoltb_pay_released_order TO evoltb_pay_payout_order;

-- 字段重命名：通知返回下发ID
ALTER TABLE evoltb_pay_payout_order
    CHANGE notify_result_released_order_id_ notify_result_payout_order_id_ varchar(100) COMMENT '通知返回下发ID';

