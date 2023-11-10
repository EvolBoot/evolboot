package org.evolboot.system.domain.bff.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author evol
 */
@Getter
@Setter
@AllArgsConstructor
public class BffDict implements Serializable {


    private Long id;

    private String displayName;

    private String key;

    private Integer sort;

    private String remark;

    private List<BffDictValue> values;


    @Setter
    @Getter
    @AllArgsConstructor
    public static class BffDictValue implements Serializable {

        private Long id;

        private Long dictKeyId;

        private String displayName;

        private String value;

        private Integer sort;

        private String remark;

    }

}
