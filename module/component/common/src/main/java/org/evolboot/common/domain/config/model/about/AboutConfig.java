package org.evolboot.common.domain.config.model.about;

import com.google.common.collect.Lists;
import lombok.*;
import org.evolboot.common.domain.config.serivce.PropertyValue;
import org.evolboot.core.DefaultConfig;
import org.evolboot.core.entity.LocaleDomainPart;
import org.evolboot.core.util.ExtendObjects;

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
