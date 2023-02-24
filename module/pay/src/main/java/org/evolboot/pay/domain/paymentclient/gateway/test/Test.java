package org.evolboot.pay.domain.paymentclient.gateway.test;

import cn.hutool.http.HttpUtil;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.security.*;

/**
 * @author evol
 */
public class Test {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.setProperty("socksProxyHost", "gate12.rola.info");
        System.setProperty("socksProxyPort", "2162");
        Authenticator.setDefault(new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                PasswordAuthentication p = new PasswordAuthentication("bajie_500", "qaz123456".toCharArray());
                return p;
            }
        });
        String s = HttpUtil.createGet("https://ipinfo.io")
                .header("Content-Type", "application/json")
                .header("accept", "application/json")
                .execute().body();
        System.out.println(s);
    }
}
