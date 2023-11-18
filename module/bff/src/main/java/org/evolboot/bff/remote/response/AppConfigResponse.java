package org.evolboot.bff.remote.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.evolboot.common.domain.config.model.about.AboutConfig;
import org.evolboot.common.domain.config.model.about.AboutConfigLocale;
import org.evolboot.common.domain.config.model.system.SystemConfig;

/**
 * @author evol
 */
@Getter
@Setter
@AllArgsConstructor
public class AppConfigResponse {


    private String terms;

    private String privacy;

    private Boolean enableRegisterSms = true;


    public static AppConfigResponse of(AboutConfig aboutConfig, SystemConfig systemConfig) {
        AboutConfigLocale locale = aboutConfig.findLocaleByCurrentLanguage(AboutConfigLocale.class);
        return new AppConfigResponse(
                locale.getTerms(),
                locale.getPrivacy(),
                systemConfig.getEnableRegisterSms()
        );
    }
}
