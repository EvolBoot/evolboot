package org.evolboot.im.domain.chatrecord.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.evolboot.core.lang.LocaleLanguage;

/**
 * @author evol
 * @date 2023-05-03 00:02:35
 */
@Getter
@Setter
@EqualsAndHashCode
public class ChatRecordLocale implements LocaleLanguage {

    private String language;


}
