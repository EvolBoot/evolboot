package org.evolboot.system.domain.dictvalue.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.evolboot.core.lang.LocaleLanguage;

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
