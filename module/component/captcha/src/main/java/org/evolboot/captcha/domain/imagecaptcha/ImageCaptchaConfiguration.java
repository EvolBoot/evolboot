package org.evolboot.captcha.domain.imagecaptcha;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.evolboot.core.exception.ConfigurationRepeatSettingException;

/**
 * @author evol
 */

public class ImageCaptchaConfiguration {

    private static Value value = Value.builder().build();

    private static boolean isInit = false;

    public static void setValue(Value value) {
        if (!isInit) {
            isInit = true;
            ImageCaptchaConfiguration.value = value;
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
        private int defaultWidth = 501;
        @Builder.Default
        private int defaultHeight = 200;
        @Builder.Default
        private int len = 3;
        // 两分钟有效期
        @Builder.Default
        private long defaultExpires = 2 * 60 * 1000;
        // 获取间隔时长
        @Builder.Default
        private long defaultIntervals = 1000L;
    }

}
