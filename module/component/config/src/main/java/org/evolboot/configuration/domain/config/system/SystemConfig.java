package org.evolboot.configuration.domain.config.system;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.evolboot.configuration.domain.config.PropertyValue;

/**
 * 基础默认配置
 *
 * @author evol
 */
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
public class SystemConfig implements PropertyValue {

    public final static String KEY = "system";


    private Boolean enableRegisterSms = true;

    private Boolean enableRegisterEmailValidation = false;

    private Boolean enableSingleLogin = true;

    @Override
    public void check() {

    }

    @Override
    public String key() {
        return KEY;
    }
}
