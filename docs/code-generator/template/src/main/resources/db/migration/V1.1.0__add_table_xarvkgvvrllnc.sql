-- 模板
DROP TABLE IF EXISTS tableprefix_lrxxoiecygkjh_xarvkgvvrllnc;
CREATE TABLE tableprefix_lrxxoiecygkjh_xarvkgvvrllnc
(
    id_        bigint   NOT NULL COMMENT '主键',
    create_at_ datetime NOT NULL COMMENT '创建时间',
    update_at_ datetime NOT NULL COMMENT '最后修改时间',
    version_   bigint   NOT NULL DEFAULT 0 COMMENT '乐观锁的版本号',

    remark_    varchar(100) COMMENT '备注',
    PRIMARY KEY (id_)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='模板';
