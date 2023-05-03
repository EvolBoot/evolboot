package org.evolboot.im.domain.groupmember;

import org.evolboot.core.lang.LocaleLanguage;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * @author evol
 * @date 2023-05-03 16:20:09
 */
@Getter
@Setter
@EqualsAndHashCode
public class GroupMemberLocale implements LocaleLanguage {

    private String language;


}
