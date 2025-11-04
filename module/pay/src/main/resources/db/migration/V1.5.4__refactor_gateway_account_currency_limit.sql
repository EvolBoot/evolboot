-- 重构支付网关账户，支持多货币金额限制

-- 删除旧的金额限制字段
ALTER TABLE evoltb_pay_gateway_account DROP COLUMN minimum_payin_amount_;
ALTER TABLE evoltb_pay_gateway_account DROP COLUMN maximum_payin_amount_;

-- 添加新的支持货币及限制字段（JSON格式）
ALTER TABLE evoltb_pay_gateway_account
ADD COLUMN support_currencies_ JSON COMMENT '支持的货币及其限制' AFTER secret_key_;
