-- 权限表增加 scope 字段
-- 说明：Permission 只需要区分平台权限和租户权限，不需要 tenant_id
-- 所有租户共享相同的租户权限定义，由平台超管统一维护

ALTER TABLE evoltb_identity_permission
ADD COLUMN scope_ VARCHAR(20) DEFAULT 'PLATFORM' COMMENT '权限域：PLATFORM-平台权限, TENANT-租户权限';

-- 更新现有数据为平台权限
UPDATE evoltb_identity_permission
SET scope_ = 'PLATFORM'
WHERE scope_ IS NULL;

-- 添加索引
CREATE INDEX idx_permission_scope ON evoltb_identity_permission(scope_);

-- 修改为 NOT NULL
ALTER TABLE evoltb_identity_permission
MODIFY COLUMN scope_ VARCHAR(20) NOT NULL DEFAULT 'PLATFORM' COMMENT '权限域：PLATFORM-平台权限, TENANT-租户权限';
