package org.evolboot.core.service.crypto.rsa;

import cn.hutool.crypto.asymmetric.RSA;

/**
 * @author evol
 */
public class RsaConfig {

    private static RSA rsa;

    public static void setRsa(RSA rsa) {
        RsaConfig.rsa = rsa;
    }

    public static RSA getRsa() {
        return rsa;
    }
}
