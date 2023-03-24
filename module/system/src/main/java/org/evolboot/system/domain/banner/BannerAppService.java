package org.evolboot.system.domain.banner;

import org.evolboot.system.domain.banner.service.BannerCreateFactory;
import org.evolboot.system.domain.banner.service.BannerUpdateService;
import org.evolboot.core.data.Page;

import java.util.List;

/**
 * banner
 *
 * @author evol
 * 
 */
public interface BannerAppService {

    Banner create(BannerCreateFactory.Request request);

    void update(Long id, BannerUpdateService.Request request);

    void delete(Long id);

    List<Banner> findAll();

    List<Banner> findAll(BannerQuery query);

    Page<Banner> page(BannerQuery query);

    Banner findById(Long id);


}
