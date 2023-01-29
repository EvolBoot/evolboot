-- 配置
DROP TABLE IF EXISTS evol_configuration;
CREATE TABLE evol_configuration
(
    key_            varchar(128) NOT NULL COMMENT '主键',
    scope_          varchar(50) COMMENT '范围',
    property_value_ longtext COMMENT '配置值',
    PRIMARY KEY (key_)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='evol_configuration';