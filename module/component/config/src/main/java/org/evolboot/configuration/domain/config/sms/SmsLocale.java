package org.evolboot.configuration.domain.config.sms;

import org.evolboot.core.DefaultConfig;
import org.evolboot.core.lang.LocaleLanguage;
import lombok.*;

/**
 * @author evol
 *
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class SmsLocale implements LocaleLanguage {


    private String language = DefaultConfig.getDefaultLanguageTag();

    private String registerSms = "Your verification code is %s";

    private String loginSms = "Your verification code is %s";

}
