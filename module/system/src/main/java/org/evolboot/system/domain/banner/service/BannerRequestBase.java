package org.evolboot.system.domain.banner.service;

import lombok.Getter;
import lombok.Setter;
import org.evolboot.system.domain.banner.entity.BannerLocale;

import java.util.List;

/**
 * banner
 *
 * @author evol
 */
@Setter
@Getter
public abstract class BannerRequestBase {


    private Integer sort;

    private Boolean show;

    private List<BannerLocale> locales;

}
