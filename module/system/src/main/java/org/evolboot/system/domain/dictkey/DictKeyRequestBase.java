package org.evolboot.system.domain.dictkey;

import lombok.Getter;
import lombok.Setter;

/**
 * 字典key
 *
 * @author evol
 * @date 2023-05-07 12:29:33
 */
@Setter
@Getter
public abstract class DictKeyRequestBase {

    private String displayName;

    private String key;

    private Integer sort;

    private String remark;


}
