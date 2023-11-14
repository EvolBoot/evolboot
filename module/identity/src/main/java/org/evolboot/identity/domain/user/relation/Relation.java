package org.evolboot.identity.domain.user.relation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import java.io.Serializable;


/**
 * 用户上下级关系
 *
 * @author evol
 */
@Table(name = "evoltb_identity_user_relation")
@Getter
@Slf4j
@NoArgsConstructor
@Entity
@IdClass(RelationId.class)
public class Relation implements Serializable {

    /**
     * 上级(祖先)
     */
    @Id
    private Long ancestor;

    /**
     * 下级
     */
    @Id
    private Long descendant;

    /**
     * 距离（层级）
     */
    @Id
    private Integer distance;

    public Relation(Long ancestor, Long descendant, Integer distance) {
        this.ancestor = ancestor;
        this.descendant = descendant;
        this.distance = distance;
    }

}
