package org.evolboot.im.domain.userconversation;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.evolboot.core.lang.LocaleLanguage;

/**
 * @author evol
 * @date 2023-05-02 23:36:54
 */
@Getter
@Setter
@EqualsAndHashCode
public class UserConversationLocale implements LocaleLanguage {

    private String language;


}
