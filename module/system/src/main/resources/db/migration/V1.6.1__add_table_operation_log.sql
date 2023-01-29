-- 操作日志
DROP TABLE IF EXISTS evol_system_operation_log;
CREATE TABLE evol_system_operation_log
(
    id_               bigint   NOT NULL COMMENT '主键',
    create_time_      datetime NOT NULL COMMENT '创建时间',
    last_modify_time_ datetime NOT NULL COMMENT '最后修改时间',
    version_          bigint   NOT NULL DEFAULT 0 COMMENT '乐观锁的版本号',

    begin_time_       bigint   NOT NULL COMMENT '开始时间',
    end_time_         bigint   NOT NULL COMMENT '结束时间',
    time_             bigint   NOT NULL COMMENT '持续时间',

    user_id_          bigint COMMENT '用户ID',
    operation_        varchar(100) COMMENT '操作名称',
    request_url_      text COMMENT 'URL',
    http_method_      varchar(50) COMMENT 'HTTP方法',
    class_method_     varchar(256) COMMENT '类和方法',
    params_           text COMMENT '执行参数',
    ip_               varchar(256) COMMENT 'IP',

    PRIMARY KEY (id_)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='操作日志';