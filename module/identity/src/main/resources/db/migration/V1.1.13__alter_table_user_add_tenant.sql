-- 用户表增加租户字段
-- 说明：记录用户所属的租户
-- - 平台用户（ROLE_SUPER_ADMIN, ROLE_STAFF）：tenant_id=NULL
-- - 租户用户（ROLE_TENANT_OWNER, ROLE_TENANT_STAFF）：tenant_id=具体租户ID
-- - 会员用户（ROLE_MEMBER）：tenant_id=NULL

ALTER TABLE evoltb_identity_user
ADD COLUMN tenant_id_ BIGINT NULL COMMENT '所属租户ID（ROLE_TENANT_OWNER/ROLE_TENANT_STAFF用户必填）';

-- 添加索引（用于快速查询租户下的所有用户）
CREATE INDEX idx_user_tenant ON evoltb_identity_user(tenant_id_);
