package org.evolboot.im.domain.friend;

import org.evolboot.core.lang.LocaleLanguage;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * @author evol
 * @date 2023-05-03 17:40:14
 */
@Getter
@Setter
@EqualsAndHashCode
public class FriendLocale implements LocaleLanguage {

    private String language;


}
