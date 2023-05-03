package org.evolboot.im.domain.chatrecord;

import org.evolboot.core.lang.LocaleLanguage;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

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
