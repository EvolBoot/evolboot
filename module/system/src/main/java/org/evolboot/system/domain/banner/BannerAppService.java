package org.evolboot.system.domain.banner;

import org.evolboot.core.data.Page;
import org.evolboot.system.domain.banner.service.BannerCreateFactory;
import org.evolboot.system.domain.banner.service.BannerUpdateService;

import java.util.List;
import java.util.Optional;

/**
 * banner
 *
 * @author evol
 */
public interface BannerAppService {

    Banner create(BannerCreateFactory.Request request);

    void update(Long id, BannerUpdateService.Request request);

    void delete(Long id);

    List<Banner> findAll();

    List<Banner> findAll(BannerQuery query);

    Page<Banner> page(BannerQuery query);

    Banner findById(Long id);


    /**
     * 根据条件查询单个
     *
     * @param query
     * @return
     */
    Optional<Banner> findOne(BannerQuery query);


}
