drop table if EXISTS evol_identity_user_role;
create TABLE evol_identity_user_role
(
    id_      bigint not null auto_increment comment '主键',
    user_id_ bigint not null comment '账户ID',
    role_id_ bigint not null comment '角色ID',
    primary key (id_)
) engine = InnoDB
  auto_increment = 1
  default charset = utf8mb4 comment ='账户与角色对应关系';