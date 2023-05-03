package org.evolboot.im.domain.userconversation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.evolboot.core.data.jpa.JpaAbstractEntity;
import org.evolboot.core.domain.AggregateRoot;
import org.evolboot.core.domain.IdGenerate;
import org.evolboot.im.domain.shared.ConversationType;
import org.evolboot.im.domain.userconversation.repository.jpa.convert.UserConversationLocaleListConverter;
import org.evolboot.core.domain.LocaleDomainPart;
import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;
import java.util.List;


/**
 * 用户会话
 *
 * @author evol
 * @date 2023-05-02 23:36:54
 */
@Table(name = "evol_im_user_conversation")
@Getter
@Slf4j
@NoArgsConstructor
@Entity
public class UserConversation extends JpaAbstractEntity<Long> implements AggregateRoot<UserConversation> {

    @Id
    private Long id;


    private Long ownerUserId;

    /**
     * 会话ID
     */
    private Long conversationId;

    /**
     * 会话类型
     */
    private ConversationType conversationType;

    /**
     * 禁言状态
     */
    private UserConversationStatus status;

    /**
     * 备注
     */
    private String remark;

    private void generateId() {
        this.id = IdGenerate.longId();
    }


    public UserConversation(String name) {
        //   setLocales(locales);
        generateId();
    }

    @Override
    public Long id() {
        return id;
    }

    @Override
    public UserConversation root() {
        return this;
    }
}
