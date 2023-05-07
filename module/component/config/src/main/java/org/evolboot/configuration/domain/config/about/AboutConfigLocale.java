package org.evolboot.configuration.domain.config.about;

import org.evolboot.core.lang.LocaleLanguage;
import lombok.*;

/**
 * @author evol
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AboutConfigLocale implements LocaleLanguage {

    private String language;

    private String terms;

    private String privacy;

    private String aboutUs;


}
