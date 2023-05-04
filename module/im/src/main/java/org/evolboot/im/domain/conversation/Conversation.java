package org.evolboot.im.domain.conversation;

import org.evolboot.core.data.jpa.JpaAbstractEntity;
import org.evolboot.core.domain.AggregateRoot;
import org.evolboot.core.domain.IdGenerate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.util.Assert;
import org.evolboot.im.domain.shared.ConversationType;

import javax.persistence.*;


/**
 * 会话
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
    private Integer quantityOfPeople;


    private void generateId() {
        this.id = IdGenerate.longId();
    }

    public Conversation addPeople(int quantityOfPeople) {
        Assert.isTrue(quantityOfPeople > 0, "增加的人员数量不能小于0");
        this.quantityOfPeople += quantityOfPeople;
        return this;
    }

    public Conversation reductionPeople(int quantityOfPeople) {
        Assert.isTrue(quantityOfPeople > 0, "减少的人员数量不能小于0");
        this.quantityOfPeople -= quantityOfPeople;
        return this;
    }

    public Conversation(ConversationType type, String relationId, int quantityOfPeople) {
        //TODO 多语言
        Assert.isTrue(quantityOfPeople > 0, "会话人员数量不能小于0");
        generateId();
        this.type = type;
        this.relationId = relationId;
        this.quantityOfPeople = quantityOfPeople;
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
