package org.evolboot.im.domain.groupapply;

import org.evolboot.core.lang.LocaleLanguage;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

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
