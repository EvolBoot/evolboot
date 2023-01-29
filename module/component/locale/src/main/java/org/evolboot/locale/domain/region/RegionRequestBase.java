package org.evolboot.locale.domain.region;

import lombok.Getter;
import lombok.Setter;

/**
 * @author evol
 */
@Setter
@Getter
public abstract class RegionRequestBase {


    private String name;
    private String shortName;
    private String mobilePrefix;
    private String flag;
    private String remark;
    private Integer sort = 0;
    private Boolean enable;
}
