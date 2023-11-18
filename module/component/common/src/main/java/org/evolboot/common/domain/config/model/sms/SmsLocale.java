package org.evolboot.common.domain.config.model.sms;

import lombok.*;
import org.evolboot.core.DefaultConfig;
import org.evolboot.core.lang.LocaleLanguage;

/**
 * @author evol
 */
@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class SmsLocale implements LocaleLanguage {


    private String language = DefaultConfig.getDefaultLanguageTag();

    private String registerSms = "Your verification code is %s";

    private String loginSms = "Your verification code is %s";

    @Builder
    public SmsLocale(String language, String registerSms, String loginSms) {
        this.language = language;
        this.registerSms = registerSms;
        this.loginSms = loginSms;
    }
}
