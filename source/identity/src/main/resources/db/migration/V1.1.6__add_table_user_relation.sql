-- 用户上下级关系
DROP TABLE IF EXISTS evol_identity_user_relation;
CREATE TABLE evol_identity_user_relation
(
    ancestor_   bigint NOT NULL COMMENT '上级',
    descendant_ bigint NOT NULL COMMENT '下级',
    distance_   int    NOT NULL COMMENT '距离',
    PRIMARY KEY (ancestor_, descendant_, distance_)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户上下级关系';

-- 默认数据
INSERT INTO evol_identity_user_relation (ancestor_, descendant_, distance_)
VALUES (0, 0, 0);