package org.evolboot.identity.domain.user.util;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author evol
 */
public abstract class UserPasswordUtil {

    private static final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    public static String encode(String password) {
        return passwordEncoder.encode(password);
    }


    public static boolean matches(CharSequence rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public static void main(String[] args) {
        String encode = UserPasswordUtil.encode("8iKCVYkNTn");
        System.out.println(encode);
    }


}
