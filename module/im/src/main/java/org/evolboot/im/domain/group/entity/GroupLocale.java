package org.evolboot.im.domain.group.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.evolboot.core.lang.LocaleLanguage;

/**
 * @author evol
 * @date 2023-05-03 15:52:47
 */
@Getter
@Setter
@EqualsAndHashCode
public class GroupLocale implements LocaleLanguage {

    private String language;


}
