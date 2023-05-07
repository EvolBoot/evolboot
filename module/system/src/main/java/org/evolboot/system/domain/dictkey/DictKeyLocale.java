package org.evolboot.system.domain.dictkey;

import org.evolboot.core.lang.LocaleLanguage;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * @author evol
 * @date 2023-05-07 12:29:33
 */
@Getter
@Setter
@EqualsAndHashCode
public class DictKeyLocale implements LocaleLanguage {

    private String language;


}
