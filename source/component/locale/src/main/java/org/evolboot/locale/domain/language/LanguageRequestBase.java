package org.evolboot.locale.domain.language;

import lombok.Getter;
import lombok.Setter;

/**
 * @author evol
 */
@Setter
@Getter
public abstract class LanguageRequestBase {

    private String remark;
    private String language;
    private Integer sort;
}
