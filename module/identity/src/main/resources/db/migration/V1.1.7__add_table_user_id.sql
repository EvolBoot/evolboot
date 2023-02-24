-- UserId
DROP TABLE IF EXISTS evoltb_identity_user_id;
CREATE TABLE evoltb_identity_user_id
(
    id_     bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
    status_ tinyint default 0 comment ' 状态',
    PRIMARY KEY (id_)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1000000
  DEFAULT CHARSET = utf8mb4 COMMENT ='UserId';
