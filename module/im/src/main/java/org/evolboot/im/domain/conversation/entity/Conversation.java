package org.evolboot.im.domain.conversation.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.data.jpa.JpaAbstractEntity;
import org.evolboot.core.entity.AggregateRoot;
import org.evolboot.core.entity.IdGenerate;
import org.evolboot.im.domain.shared.ConversationType;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


/**
 * 会话
 * TODO 多语言
 *
 * @author evol
 * @date 2023-05-02 21:43:03
 */
@Table(name = "evoltb_im_conversation")
@Getter
@Slf4j
@NoArgsConstructor
@Entity
@Schema(title = "会话")
public class Conversation extends JpaAbstractEntity<Long> implements AggregateRoot<Conversation> {

    /**
     * 会话ID
     */
    @Id
    private Long id;

    /**
     * 会话类型
     */
    @Schema(title = "会话类型")
    private ConversationType type;

    /**
     * 关联的ID
     */
    @Schema(title = "关联的ID")
    private String relationId;

    /**
     * 人数
     */
    @Schema(title = "人数")
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
