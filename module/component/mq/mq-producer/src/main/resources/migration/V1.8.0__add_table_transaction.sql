-- Mq事务
DROP TABLE IF EXISTS evoltb_mq_producer_mq_transaction;
CREATE TABLE evoltb_mq_producer_mq_transaction
(
    id_          bigint   NOT NULL COMMENT '主键',
    create_time_ datetime NOT NULL COMMENT '创建时间',
    remark_      varchar(100) COMMENT '备注',

    PRIMARY KEY (id_)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='Mq事务';
