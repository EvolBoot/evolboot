package org.evolboot.core.service.crypto.rsa;

/**
 * Rsa 加密解密服务
 *
 * @author evol
 */
public interface RsaService {

    /**
     * 公钥加密
     *
     * @param str
     * @return
     */
    String publicEncrypt(String str);

    /**
     * 私钥加密
     *
     * @param str
     * @return
     */
    String privateEncrypt(String str);

    /**
     * 私钥解密
     *
     * @param str
     * @return
     */
    String privateDecryptStr(String str);

    /**
     * 解密成功指定对象
     *
     * @param str
     * @param clazz
     * @param <T>
     * @return
     */
    <T> T privateDecryptStr(String str, Class<T> clazz);

    /**
     * 公钥解密
     *
     * @param str
     * @return
     */
    String publicDecryptStr(String str);


}
