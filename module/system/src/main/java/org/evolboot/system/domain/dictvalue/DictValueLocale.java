package org.evolboot.system.domain.dictvalue;

import org.evolboot.core.lang.LocaleLanguage;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * @author evol
 * @date 2023-05-07 12:55:06
 */
@Getter
@Setter
@EqualsAndHashCode
public class DictValueLocale implements LocaleLanguage {

    private String language;


}
