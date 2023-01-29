package org.evolboot.core.util;


import cn.hutool.core.codec.Base32;
import cn.hutool.core.util.NumberUtil;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


public class GoogleAuthenticator {

    private final static GoogleAuthenticator ga = new GoogleAuthenticator();

    public static final int SECRET_SIZE = 10;

    public static final String SEED = "IGXLMtNkZTAcQ5R8pSFDJtXs3WfmRij7EVcW6PH4rvPsyDvRse9mDRLndulaqMp0tYrg1LsJRl39";

    public static final String RANDOM_NUMBER_ALGORITHM = "SHA1PRNG";

    int window_size = 0; // 0-17,容忍度, 0 表示一过期就不行, 1 表示还可以用一轮

    public void setWindowSize(int s) {
        window_size = s;
    }

    /**
     * 验证身份验证码是否正确
     *
     * @param codes       输入的身份验证码
     * @param savedSecret 密钥
     * @return
     */
    public static Boolean authcode(String codes, String savedSecret) {
        long code = 0;
        if (NumberUtil.isNumber(codes)) {
            code = Long.parseLong(codes);
        } else {
            return false;
        }
        long t = System.currentTimeMillis();
        ga.setWindowSize(ga.window_size); // should give 5 * 30 seconds of grace...
        boolean r = ga.check_code(savedSecret, code, t);
        return r;
    }

    /**
     * 获取密钥
     *
     * @param user 用户
     * @param host 域
     * @return 密钥
     */
    public static String genSecret(String user, String host) {
        String secret = GoogleAuthenticator.generateSecretKey();
        GoogleAuthenticator.getQRBarcodeURL(user, host, secret);
        return secret;
    }

    public static String generateSecretKey() {
        SecureRandom sr = null;
        try {
            sr = SecureRandom.getInstance(RANDOM_NUMBER_ALGORITHM);
            sr.setSeed(Base64.decodeBase64(SEED));
            byte[] buffer = sr.generateSeed(SECRET_SIZE);
            String bEncodedKey = Base32.encode(buffer);
            String encodedKey = bEncodedKey;
            return encodedKey;
        } catch (NoSuchAlgorithmException e) {
            // should never occur... configuration error
        }
        return null;
    }

    /**
     * 获取二维码图片URL
     *
     * @param user   用户
     * @param host   域
     * @param secret 密钥
     * @return 二维码URL
     */
    public static String getQRBarcodeURL(String user, String host, String secret) {
        String format = "https://www.google.com/chart?chs=200x200&chld=M%%7C0&cht=qr&chl=otpauth://totp/%s@%s%%3Fsecret%%3D%s";
        return String.format(format, user, host, secret);
    }

    private boolean check_code(String secret, long code, long timeMsec) {
        byte[] decodedKey = Base32.decode(secret);
        long t = (timeMsec / 1000L) / 30L;
        for (int i = -window_size; i <= window_size; ++i) {
            long hash;
            try {
                hash = verify_code(decodedKey, t + i);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }
            if (hash == code) {
                return true;
            }
        }
        return false;
    }

    private static int verify_code(byte[] key, long t)
            throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] data = new byte[8];
        long value = t;
        for (int i = 8; i-- > 0; value >>>= 8) {
            data[i] = (byte) value;
        }
        SecretKeySpec signKey = new SecretKeySpec(key, "HmacSHA1");
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(signKey);
        byte[] hash = mac.doFinal(data);
        int offset = hash[20 - 1] & 0xF;
        long truncatedHash = 0;
        for (int i = 0; i < 4; ++i) {
            truncatedHash <<= 8;
            truncatedHash |= (hash[offset + i] & 0xFF);
        }
        truncatedHash &= 0x7FFFFFFF;
        truncatedHash %= 1000000;
        return (int) truncatedHash;
    }

    public static void main(String[] args) {
        String s = genSecret("test", "test");
    }


}