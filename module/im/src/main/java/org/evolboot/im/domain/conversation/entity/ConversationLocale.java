package org.evolboot.im.domain.conversation.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.evolboot.core.lang.LocaleLanguage;

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
