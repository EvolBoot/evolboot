package org.evolboot.identity.domain.user.relation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author evol
 * 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RelationId implements Serializable {

    private Long ancestor;


    private Long descendant;


    private Integer distance;


}
