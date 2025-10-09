-- 存储表增加资源归属类型
-- 说明：区分资源的归属者类型
-- - USER: 个人资源（如用户头像），归属于用户，owner_id=用户ID
-- - TENANT: 租户资源（如商品图片），归属于租户，owner_id=租户ID
-- - PLATFORM: 平台资源（如系统图标），归属于平台，owner_id=0

ALTER TABLE evoltb_storage_blob
ADD COLUMN owner_type_ VARCHAR(20) DEFAULT 'USER' COMMENT '归属类型：USER-个人, TENANT-租户, PLATFORM-平台',
ADD COLUMN owner_id_ BIGINT NOT NULL DEFAULT 0 COMMENT '归属ID（根据owner_type_对应用户ID或租户ID）';

-- 迁移现有数据：owner_user_id_ -> owner_id_
UPDATE evoltb_storage_blob
SET owner_type_ = 'USER',
    owner_id_ = COALESCE(owner_user_id_, 0)
WHERE 1=1;

-- 重命名原字段为创建者字段（用于审计，记录谁创建的资源）
ALTER TABLE evoltb_storage_blob
CHANGE COLUMN owner_user_id_ creator_user_id_ BIGINT NULL COMMENT '创建者用户ID（用于审计）';

-- 添加联合索引（owner_type + owner_id 用于查询归属资源）
CREATE INDEX idx_blob_owner ON evoltb_storage_blob(owner_type_, owner_id_);

-- 修改为 NOT NULL
ALTER TABLE evoltb_storage_blob
MODIFY COLUMN owner_type_ VARCHAR(20) NOT NULL DEFAULT 'USER' COMMENT '归属类型：USER-个人, TENANT-租户, PLATFORM-平台',
MODIFY COLUMN owner_id_ BIGINT NOT NULL COMMENT '归属ID';
