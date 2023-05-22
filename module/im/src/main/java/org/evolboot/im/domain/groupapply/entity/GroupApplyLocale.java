package org.evolboot.im.domain.groupapply.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.evolboot.core.lang.LocaleLanguage;

/**
 * @author evol
 * @date 2023-05-03 17:27:04
 */
@Getter
@Setter
@EqualsAndHashCode
public class GroupApplyLocale implements LocaleLanguage {

    private String language;


}
