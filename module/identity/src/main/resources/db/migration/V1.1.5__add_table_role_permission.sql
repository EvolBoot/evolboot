drop table if EXISTS evoltb_identity_role_permission;
create TABLE evoltb_identity_role_permission
(
    id_            bigint not null auto_increment comment '主键',
    role_id_       bigint not null comment '角色ID',
    permission_id_ bigint not null comment '权限ID',
    primary key (id_)
) engine = InnoDB
  auto_increment = 1
  default charset = utf8mb4 comment ='角色与权限对应关系';