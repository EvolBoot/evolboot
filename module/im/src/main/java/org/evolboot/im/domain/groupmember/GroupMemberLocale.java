package org.evolboot.im.domain.groupmember;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.evolboot.core.lang.LocaleLanguage;

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
