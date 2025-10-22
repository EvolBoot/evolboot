-- 将第三方代收订单表与字段从 receipt_* 重命名为 payin_*
-- 注意：生产执行前请先备份数据库

-- 1) 重命名表 evoltb_pay_receipt_order -> evoltb_pay_payin_order
SET @src_table := 'evoltb_pay_receipt_order';
SET @dst_table := 'evoltb_pay_payin_order';

-- 如果目标表不存在且源表存在，则执行重命名
SET @exists_src := (SELECT COUNT(*) FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = @src_table);
SET @exists_dst := (SELECT COUNT(*) FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = @dst_table);

-- 不能在 MySQL 中用 IF 控制流程（Flyway 逐条执行），因此使用容错写法：
-- 若表已存在会失败但可忽略；若不存在则不影响后续语句
RENAME TABLE evoltb_pay_receipt_order TO evoltb_pay_payin_order;

-- 2) 重命名列 notify_result_receipt_order_id_ -> notify_result_payin_order_id_
-- 列类型沿用原表定义（varchar(50)）
ALTER TABLE evoltb_pay_payin_order 
    CHANGE COLUMN notify_result_receipt_order_id_ notify_result_payin_order_id_ varchar(50) COMMENT '通知返回的我方订单ID';

