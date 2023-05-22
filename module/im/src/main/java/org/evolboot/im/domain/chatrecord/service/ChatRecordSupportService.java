package org.evolboot.im.domain.chatrecord.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.exception.DomainNotFoundException;
import org.evolboot.core.i18n.I18NMessageHolder;
import org.evolboot.im.ImI18nMessage;
import org.evolboot.im.domain.chatrecord.entity.ChatRecord;
import org.evolboot.im.domain.chatrecord.repository.ChatRecordRepository;

/**
 * 聊天记录
 *
 * @author evol
 * @date 2023-05-03 00:02:35
 */
@Slf4j
public abstract class ChatRecordSupportService {

    protected final ChatRecordRepository repository;

    protected ChatRecordSupportService(ChatRecordRepository repository) {
        this.repository = repository;
    }

    public ChatRecord findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new DomainNotFoundException(I18NMessageHolder.message(ImI18nMessage.ChatRecord.NOT_FOUND)));
    }

}
