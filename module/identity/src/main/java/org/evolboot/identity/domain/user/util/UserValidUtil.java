package org.evolboot.identity.domain.user.util;


import org.evolboot.core.util.RegexUtil;

/**
 * @author evol
 */
public abstract class UserValidUtil {

    /**
     * 验证码简单密码
     *
     * @param password 6-20 非空字符
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkPassword(String password) {
        return password != null && RegexUtil.checkSimplePassword(password);
    }

    /**
     * 验证用户名
     *
     * @param username 字母开头，6-16位数
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkUsername(String username) {

        return username != null && RegexUtil.checkUsername(username);
    }


}
