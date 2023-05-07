package org.evolboot.system.domain.dictvalue;

import lombok.Getter;
import lombok.Setter;

/**
 * 字典Value
 *
 * @author evol
 * @date 2023-05-07 12:55:06
 */
@Setter
@Getter
public abstract class DictValueRequestBase {

    private Long dictKeyId;
    ;
    private String displayName;

    private String value;

    private Integer sort;

    private String remark;

}
