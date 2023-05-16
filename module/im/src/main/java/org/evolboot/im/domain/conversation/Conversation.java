package org.evolboot.im.domain.conversation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.data.jpa.JpaAbstractEntity;
import org.evolboot.core.domain.AggregateRoot;
import org.evolboot.core.domain.IdGenerate;
import org.evolboot.im.domain.shared.ConversationType;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 会话
 * TODO 多语言
 *
 * @author evol
 * @date 2023-05-02 21:43:03
 */
@Table(name = "evol_im_conversation")
@Getter
@Slf4j
@NoArgsConstructor
@Entity
public class Conversation extends JpaAbstractEntity<Long> implements AggregateRoot<Conversation> {

    /**
     * 会话ID
     */
    @Id
    private Long id;

    /**
     * 会话类型
     */
    private ConversationType type;

    /**
     * 关联的ID
     */
    private String relationId;

    /**
     * 人数
     */
    private Integer quantityOfPeople = 0;


    private void generateId() {
        this.id = IdGenerate.longId();
    }

    public Conversation addPeople() {
        this.quantityOfPeople++;
        return this;
    }

    public Conversation reductionPeople() {
        this.quantityOfPeople--;
        return this;
    }

    public Conversation(ConversationType type, String relationId) {
        generateId();
        this.type = type;
        this.relationId = relationId;
    }


    @Override
    public Long id() {
        return id;
    }

    @Override
    public Conversation root() {
        return this;
    }
}
