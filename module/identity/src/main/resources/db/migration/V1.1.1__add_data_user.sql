-- 默认密码 8iKCVYkNTn
INSERT INTO evoltb_identity_user (id_, create_at_, update_at_, version_, username_, email_, mobile_, password_,
                                  user_identity_, identity_symbol_, avatar_, nickname_, status_, user_type_)
VALUES (1, '2020-07-02 17:51:34', '2020-07-02 17:51:36', 1, 'root', 'root@gmail.com', '18888888888',
        '{bcrypt}$2a$10$5NXb5MrxA9RdPJ.PHYEDCukEa4ohOzapjNYB/1gZqvbTi8nw6RFQG', '["ROLE_ADMIN"]', 1,
        '${default-avatar}', 'root', 'ACTIVE', 'NORMAL');

-- 默认密码 123456
INSERT INTO evoltb_identity_user (id_, create_at_, update_at_, version_, username_, email_, mobile_, password_,
                                  user_identity_, identity_symbol_, avatar_, nickname_, status_, user_type_)
VALUES (2, '2020-07-02 17:51:34', '2020-07-02 17:51:36', 1, 'evol', 'evol@qq.com', '17777777777',
        '{bcrypt}$2a$10$7vUjiIMGJON/uopOakv7HeX13/dOVydgiN3gkQsBtXJ.L88Qhcscy', '["ROLE_MEMBER"]', 2,
        '${default-avatar}', 'root', 'ACTIVE', 'NORMAL');