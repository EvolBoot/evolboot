package org.evolboot.content.domain.banner;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * banner
 *
 * @author evol
 * 
 */
@Setter
@Getter
public abstract class BannerRequestBase {


    private Integer sort;

    private Boolean show;

    private List<BannerLocale> locales;

}
