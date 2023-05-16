package org.evolboot.captcha.domain.mobilecaptcha;

import cn.hutool.core.date.DateUnit;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.evolboot.core.exception.ConfigurationRepeatSettingException;

/**
 * @author evol
 */
public class MobileCaptchaConfiguration {

    private static Value value = Value.builder().build();

    private static boolean isInit = false;

    public static void setValue(Value value) {
        if (!isInit) {
            isInit = true;
            MobileCaptchaConfiguration.value = value;
            return;
        }
        throw new ConfigurationRepeatSettingException();
    }

    public static Value getValue() {
        return value;
    }

    @Builder
    @Getter
    @ToString
    public static class Value {
        @Builder.Default
        private int limitVerifyCount = 5;

        @Builder.Default
        private long interval = DateUnit.MINUTE.getMillis();

        @Builder.Default
        private long expires = 5 * DateUnit.MINUTE.getMillis();

    }

}
