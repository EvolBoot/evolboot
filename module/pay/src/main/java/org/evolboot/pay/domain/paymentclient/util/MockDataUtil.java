package org.evolboot.pay.domain.paymentclient.util;

import cn.hutool.core.util.RandomUtil;
import org.evolboot.core.util.CodeGeneraterUtil;

/**
 * @author evol
 */
public class MockDataUtil {

    public static String buildIndiaPhone() {
        return "9162" + CodeGeneraterUtil.get4Number() + CodeGeneraterUtil.get4Number();
    }

    public static String buildRandomEmail(String name) {
        return name + RandomUtil.randomString(2) + "@evolboot.com";
    }

}
