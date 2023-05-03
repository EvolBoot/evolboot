package org.evolboot.im.domain.userconversation;

import org.evolboot.core.lang.LocaleLanguage;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

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
