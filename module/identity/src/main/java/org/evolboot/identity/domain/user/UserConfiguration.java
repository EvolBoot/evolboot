package org.evolboot.identity.domain.user;

import lombok.Builder;
import lombok.Getter;
import org.evolboot.core.exception.ConfigurationRepeatSettingException;

/**
 * @author evol
 */
public final class UserConfiguration {

    private static Value value = Value.builder().build();

    private static boolean isInit = false;

    public static void setValue(Value value) {
        if (!isInit) {
            isInit = true;
            UserConfiguration.value = value;
            return;
        }
        throw new ConfigurationRepeatSettingException();
    }

    public static Value getValue() {
        return value;
    }

    @Builder
    @Getter
    public static class Value {
        @Builder.Default
        private String defaultAvatar = "https://dn-qiniu-avatar.qbox.me/avatar/";
    }

}
