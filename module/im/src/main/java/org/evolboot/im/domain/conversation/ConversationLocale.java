package org.evolboot.im.domain.conversation;

import org.evolboot.core.lang.LocaleLanguage;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * @author evol
 * @date 2023-05-02 21:43:03
 */
@Getter
@Setter
@EqualsAndHashCode
public class ConversationLocale implements LocaleLanguage {

    private String language;


}
