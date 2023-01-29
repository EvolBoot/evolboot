package org.evolboot.core.service.crypto.rsa;

import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import org.evolboot.core.util.JsonUtil;

/**
 * @author evol
 * 
 */
public class DefaultRsaService implements RsaService {

    private final RSA rsa;

    public DefaultRsaService(RSA rsa) {
        this.rsa = rsa;
    }

    @Override
    public String publicEncrypt(String str) {
        return rsa.encryptBase64(str, KeyType.PublicKey);
    }

    @Override
    public String privateEncrypt(String str) {
        return rsa.encryptBase64(str, KeyType.PrivateKey);
    }

    @Override
    public String privateDecryptStr(String str) {
        return rsa.decryptStr(str, KeyType.PrivateKey);
    }

    @Override
    public <T> T privateDecryptStr(String str, Class<T> clazz) {
        T parse = JsonUtil.parse(privateDecryptStr(str), clazz);
        return parse;
    }

    @Override
    public String publicDecryptStr(String str) {
        return rsa.decryptStr(str, KeyType.PublicKey);
    }
}
