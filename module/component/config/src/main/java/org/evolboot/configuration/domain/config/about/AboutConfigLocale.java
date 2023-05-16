package org.evolboot.configuration.domain.config.about;

import lombok.*;
import org.evolboot.core.lang.LocaleLanguage;

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
