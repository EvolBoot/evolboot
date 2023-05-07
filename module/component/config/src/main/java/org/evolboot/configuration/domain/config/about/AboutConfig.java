package org.evolboot.configuration.domain.config.about;

import org.evolboot.configuration.domain.config.PropertyValue;
import org.evolboot.core.DefaultConfig;
import org.evolboot.core.domain.LocaleDomainPart;
import org.evolboot.core.util.ExtendObjects;
import com.google.common.collect.Lists;
import lombok.*;

import java.util.List;

/**
 * @author evol
 */
@Data
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
public class AboutConfig implements PropertyValue, LocaleDomainPart<AboutConfigLocale> {

    public final static String KEY = "about";

    public static final List<AboutConfigLocale> DEFAULT = Lists.newArrayList(new AboutConfigLocale(
            DefaultConfig.getDefaultLanguageTag(),
            "https://www.baidu.com",
            "https://www.baidu.com",
            "https://www.baidu.com"
    ));

    private List<AboutConfigLocale> locales;


    @Override
    public void check() {

    }

    @Override
    public String key() {
        return KEY;
    }

    @Override
    public List<AboutConfigLocale> getLocales() {
        if (ExtendObjects.isEmpty(locales)) {
            return DEFAULT;
        }
        return locales;
    }
}
