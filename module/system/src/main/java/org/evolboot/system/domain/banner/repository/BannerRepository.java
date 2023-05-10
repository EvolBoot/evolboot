package org.evolboot.system.domain.banner.repository;

import org.evolboot.system.domain.banner.Banner;
import org.evolboot.system.domain.banner.BannerQuery;
import org.evolboot.core.data.Page;

import java.util.List;
import java.util.Optional;

/**
 * banner
 *
 * @author evol
 * 
 */
public interface BannerRepository {

    Banner save(Banner banner);

    Optional<Banner> findById(Long id);

    Page<Banner> page(BannerQuery query);

    void deleteById(Long id);

    List<Banner> findAll();

    List<Banner> findAll(BannerQuery query);

    /**
     * 根据条件查询单个
     * @param query
     * @return
     */
    Optional<Banner> findOne(BannerQuery query);
}
