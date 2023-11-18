package org.evolboot.common.domain.dictkey.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.evolboot.core.lang.LocaleLanguage;

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
