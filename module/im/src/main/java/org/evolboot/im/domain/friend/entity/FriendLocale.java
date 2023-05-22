package org.evolboot.im.domain.friend.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.evolboot.core.lang.LocaleLanguage;

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
