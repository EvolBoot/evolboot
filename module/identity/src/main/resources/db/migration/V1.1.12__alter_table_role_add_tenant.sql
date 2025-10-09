-- 角色表增加租户相关字段
-- 说明：Role 需要区分平台角色和租户角色
-- - 平台角色：scope=PLATFORM, tenant_id=NULL
-- - 租户角色：scope=TENANT, tenant_id=具体租户ID（用于数据隔离）

ALTER TABLE evoltb_identity_role
ADD COLUMN scope_ VARCHAR(20) DEFAULT 'PLATFORM' COMMENT '角色域：PLATFORM-平台角色, TENANT-租户角色',
ADD COLUMN tenant_id_ BIGINT NULL COMMENT '租户ID（scope=TENANT时必填，用于数据隔离）';

-- 更新现有数据为平台角色
UPDATE evoltb_identity_role
SET scope_ = 'PLATFORM'
WHERE scope_ IS NULL;

-- 添加联合索引（scope + tenant_id 用于查询过滤）
CREATE INDEX idx_role_scope_tenant ON evoltb_identity_role(scope_, tenant_id_);

-- 添加单独索引（tenant_id 用于查找租户的所有角色）
CREATE INDEX idx_role_tenant ON evoltb_identity_role(tenant_id_);

-- 修改为 NOT NULL
ALTER TABLE evoltb_identity_role
MODIFY COLUMN scope_ VARCHAR(20) NOT NULL DEFAULT 'PLATFORM' COMMENT '角色域：PLATFORM-平台角色, TENANT-租户角色';
