INSERT INTO evoltb_identity_permission (id_, create_at_, update_at_, version_, parent_id_, parent_ids_, code_,
                                        component_, path_, type_, sort_, title_, icon_, is_hide_, is_keep_alive_,
                                        is_affix_, is_link_, link_, is_iframe_, perm_, layout_, scope_)
VALUES (1, '2023-05-25 09:16:31', '2025-10-21 17:07:09', 9, 0, '[]', 'AdminHome', '/src/views/admin/home/index.vue',
        '/admin/index', 1, 999, '首页', 'i-fe:grid', 0, 1, 0, 0, '', 0, '', null, 'PLATFORM');
INSERT INTO evoltb_identity_permission (id_, create_at_, update_at_, version_, parent_id_, parent_ids_, code_,
                                        component_, path_, type_, sort_, title_, icon_, is_hide_, is_keep_alive_,
                                        is_affix_, is_link_, link_, is_iframe_, perm_, layout_, scope_)
VALUES (2, '2023-05-25 09:16:31', '2025-10-03 17:32:39', 5, 0, '[]', '-system', 'layout/routerView/parent', '/system',
        1, 13, '系统设置', 'i-fe:settings', 0, 1, 0, 0, '', 0, '', null, 'PLATFORM');
INSERT INTO evoltb_identity_permission (id_, create_at_, update_at_, version_, parent_id_, parent_ids_, code_,
                                        component_, path_, type_, sort_, title_, icon_, is_hide_, is_keep_alive_,
                                        is_affix_, is_link_, link_, is_iframe_, perm_, layout_, scope_)
VALUES (3, '2023-05-25 09:16:31', '2025-10-21 15:07:17', 12, 2, '[2]', '-system-menu',
        '/src/views/admin/pms/resource/index.vue', '/system/menu', 1, 0, '菜单管理', 'i-fe:table', 0, 1, 0, 0, '', 0,
        'permission_page;permission_update;permission_delete;permission_create;bff_authority', '', 'PLATFORM');
INSERT INTO evoltb_identity_permission (id_, create_at_, update_at_, version_, parent_id_, parent_ids_, code_,
                                        component_, path_, type_, sort_, title_, icon_, is_hide_, is_keep_alive_,
                                        is_affix_, is_link_, link_, is_iframe_, perm_, layout_, scope_)
VALUES (4, '2023-05-25 09:16:31', '2025-10-21 14:59:50', 10, 2, '[2]', 'UserMgt', '/src/views/admin/pms/user/index.vue',
        '/system/user', 1, 0, '员工管理', 'i-fe:user', 0, 1, 0, 0, '', 0,
        'user_role_update;user_create;user_update;user_page', '', 'PLATFORM');
INSERT INTO evoltb_identity_permission (id_, create_at_, update_at_, version_, parent_id_, parent_ids_, code_,
                                        component_, path_, type_, sort_, title_, icon_, is_hide_, is_keep_alive_,
                                        is_affix_, is_link_, link_, is_iframe_, perm_, layout_, scope_)
VALUES (5, '2023-05-25 09:16:31', '2025-10-21 14:58:30', 10, 2, '[2]', 'RoleUser',
        '/src/views/admin/pms/role/index.vue', '/system/role', 1, 0, '角色管理', 'i-fe:users', 0, 1, 0, 0, '', 0,
        'role_page;role_update;role_delete;role_create;identity_userrole_single;identity_userrole_page', '',
        'PLATFORM');
INSERT INTO evoltb_identity_permission (id_, create_at_, update_at_, version_, parent_id_, parent_ids_, code_,
                                        component_, path_, type_, sort_, title_, icon_, is_hide_, is_keep_alive_,
                                        is_affix_, is_link_, link_, is_iframe_, perm_, layout_, scope_)
VALUES (6, '2023-05-27 12:14:12', '2023-05-27 12:14:12', 0, 21, '[2, 21]', '', '', '', 0, 0, '增加员工',
        'i-fe:toggle-right', 0, 1, 0, null, '', 0, 'user_create', null, 'PLATFORM');
INSERT INTO evoltb_identity_permission (id_, create_at_, update_at_, version_, parent_id_, parent_ids_, code_,
                                        component_, path_, type_, sort_, title_, icon_, is_hide_, is_keep_alive_,
                                        is_affix_, is_link_, link_, is_iframe_, perm_, layout_, scope_)
VALUES (7, '2023-05-27 16:06:05', '2025-10-03 17:31:07', 10, 2, '[2]', 'Dict', '/src/views/admin/pms/dict/index.vue',
        '/system/dic', 1, 10, '字典管理', 'i-fe:list', 0, 1, 0, 0, '', 0,
        'common_dictkey_single;common_dictkey_update;common_dictkey_delete;common_dictkey_create;common_dictkey_page;common_dictvalue_single;common_dictvalue_update;common_dictvalue_delete;common_dictvalue_create;common_dictvalue_page',
        '', 'PLATFORM');
INSERT INTO evoltb_identity_permission (id_, create_at_, update_at_, version_, parent_id_, parent_ids_, code_,
                                        component_, path_, type_, sort_, title_, icon_, is_hide_, is_keep_alive_,
                                        is_affix_, is_link_, link_, is_iframe_, perm_, layout_, scope_)
VALUES (8, '2023-05-30 15:21:34', '2025-10-21 14:58:56', 11, 2, '[2]', 'LoginLog',
        '/src/views/admin/pms/login-log/index.vue', '/system/login-log/index', 1, 0, '登录日志', 'i-fe:mouse-pointer',
        0, 1, 0, 0, '', 0, 'system_userloginlog_page', '', 'PLATFORM');
INSERT INTO evoltb_identity_permission (id_, create_at_, update_at_, version_, parent_id_, parent_ids_, code_,
                                        component_, path_, type_, sort_, title_, icon_, is_hide_, is_keep_alive_,
                                        is_affix_, is_link_, link_, is_iframe_, perm_, layout_, scope_)
VALUES (9, '2023-05-30 15:39:45', '2025-10-21 14:59:10', 21, 2, '[2]', 'OperationLog',
        '/src/views/admin/pms/operation-log/index.vue', '/system/operation-log/index', 1, 0, '操作日志', 'i-fe:map', 0,
        1, 0, 0, '', 0, 'system_operationlog_single;system_operationlog_page', '', 'PLATFORM');
INSERT INTO evoltb_identity_permission (id_, create_at_, update_at_, version_, parent_id_, parent_ids_, code_,
                                        component_, path_, type_, sort_, title_, icon_, is_hide_, is_keep_alive_,
                                        is_affix_, is_link_, link_, is_iframe_, perm_, layout_, scope_)
VALUES (314470687572032, '2025-10-02 21:08:29', '2025-10-21 15:00:30', 1, 2, '[2]', '-system-file',
        '/src/views/admin/pms/file/index.vue', '/system/file', 1, 1, '文件管理', 'i-fe:monitor', 0, 1, 0, 0, '', 0,
        'storage_bolb_delete;storage_bolb_create;storage_bolb_page', '', 'PLATFORM');
INSERT INTO evoltb_identity_permission (id_, create_at_, update_at_, version_, parent_id_, parent_ids_, code_,
                                        component_, path_, type_, sort_, title_, icon_, is_hide_, is_keep_alive_,
                                        is_affix_, is_link_, link_, is_iframe_, perm_, layout_, scope_)
VALUES (315678567432256, '2025-10-09 13:07:52', '2025-10-09 13:07:52', 0, 0, '[]', 'tenant',
        '/src/views/admin/tenant/user/index.vue', '/tenant', 1, 1, '租户管理', 'i-fe:users', 0, 0, 0, null, '', 0, '',
        '', 'PLATFORM');
INSERT INTO evoltb_identity_permission (id_, create_at_, update_at_, version_, parent_id_, parent_ids_, code_,
                                        component_, path_, type_, sort_, title_, icon_, is_hide_, is_keep_alive_,
                                        is_affix_, is_link_, link_, is_iframe_, perm_, layout_, scope_)
VALUES (315678626152512, '2025-10-09 13:08:19', '2025-10-21 15:01:50', 1, 315678567432256, '[315678567432256]',
        'TenantMgt', '/src/views/admin/tenant/user/index.vue', '/tenant/user', 1, 1, '租户用户', 'i-fe:user', 0, 1, 0,
        0, '', 0,
        'permission_create;permission_delete;permission_update;permission_page;user_state_lock;user_page;user_state_active;user_create;user_update',
        '', 'PLATFORM');
INSERT INTO evoltb_identity_permission (id_, create_at_, update_at_, version_, parent_id_, parent_ids_, code_,
                                        component_, path_, type_, sort_, title_, icon_, is_hide_, is_keep_alive_,
                                        is_affix_, is_link_, link_, is_iframe_, perm_, layout_, scope_)
VALUES (315678728912960, '2025-10-09 13:09:08', '2025-10-21 15:01:18', 1, 315678567432256, '[315678567432256]',
        'TenantResourceMgt', '/src/views/admin/tenant/resource/index.vue', '/tenant/resource', 1, 1, '租户菜单',
        'i-me:dialog', 0, 1, 0, 0, '', 0, 'permission_create;permission_update;permission_delete;permission_page', '',
        'PLATFORM');
INSERT INTO evoltb_identity_permission (id_, create_at_, update_at_, version_, parent_id_, parent_ids_, code_,
                                        component_, path_, type_, sort_, title_, icon_, is_hide_, is_keep_alive_,
                                        is_affix_, is_link_, link_, is_iframe_, perm_, layout_, scope_)
VALUES (315678766661696, '2025-10-09 13:09:26', '2025-10-21 17:07:45', 4, 0, '[]', 'TenantHome',
        '/src/views/tenant/home/index.vue', '/tenant/index', 1, 100, '首页', 'i-simple-icons:juejin', 0, 1, 0, 0, '', 0,
        '', '', 'TENANT');
INSERT INTO evoltb_identity_permission (id_, create_at_, update_at_, version_, parent_id_, parent_ids_, code_,
                                        component_, path_, type_, sort_, title_, icon_, is_hide_, is_keep_alive_,
                                        is_affix_, is_link_, link_, is_iframe_, perm_, layout_, scope_)
VALUES (317676702400576, '2025-10-20 13:47:37', '2025-10-21 15:02:03', 1, 0, '[]', 'MemberManagement',
        '/src/views/admin/member/index.vue', '/admin/member', 1, 1, '会员管理', 'i-fe:users', 0, 1, 0, 0, '', 0,
        'user_role_update;user_state_lock;user_update;user_delete;user_create;user_state_active;user_password_reset;user_page',
        '', 'PLATFORM');
INSERT INTO evoltb_identity_permission (id_, create_at_, update_at_, version_, parent_id_, parent_ids_, code_,
                                        component_, path_, type_, sort_, title_, icon_, is_hide_, is_keep_alive_,
                                        is_affix_, is_link_, link_, is_iframe_, perm_, layout_, scope_)
VALUES (317683950157888, '2025-10-20 14:45:12', '2025-10-21 15:00:39', 1, 2, '[2]', 'SystemConfig',
        '/src/views/admin/pms/config/index.vue', '/admin/config', 1, 2, '系统配置', 'i-fe:settings', 0, 1, 0, 0, '', 0,
        'common_config_update;common_config_single', '', 'PLATFORM');
INSERT INTO evoltb_identity_permission (id_, create_at_, update_at_, version_, parent_id_, parent_ids_, code_,
                                        component_, path_, type_, sort_, title_, icon_, is_hide_, is_keep_alive_,
                                        is_affix_, is_link_, link_, is_iframe_, perm_, layout_, scope_)
VALUES (317754617888832, '2025-10-21 00:06:49', '2025-10-21 00:06:49', 0, 0, '[]', 'TenantRoleMgt',
        '/src/views/tenant/pms/role/index.vue', '/tenant/role', 1, 1, '租户角色', 'i-fe:users', 0, 1, 0, null, '', 0,
        '', '', 'TENANT');
INSERT INTO evoltb_identity_permission (id_, create_at_, update_at_, version_, parent_id_, parent_ids_, code_,
                                        component_, path_, type_, sort_, title_, icon_, is_hide_, is_keep_alive_,
                                        is_affix_, is_link_, link_, is_iframe_, perm_, layout_, scope_)
VALUES (317759267274816, '2025-10-21 00:43:47', '2025-10-21 00:43:47', 0, 0, '[]', 'TenantUserMgt',
        '/src/views/tenant/pms/user/index.vue', '/tenant/user', 1, 3, '员工管理', 'i-fe:user', 0, 1, 0, null, '', 0, '',
        '', 'TENANT');
