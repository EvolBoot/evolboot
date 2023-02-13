-- 配置
DROP TABLE IF EXISTS evol_config;
CREATE TABLE evol_config
(
    key_            varchar(128) NOT NULL COMMENT '主键',
    scope_          varchar(50) COMMENT '范围',
    property_value_ json COMMENT '配置值',
    PRIMARY KEY (key_)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='配置';