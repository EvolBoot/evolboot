package org.evolboot.content.domain.banner.repository;

import org.evolboot.content.domain.banner.Banner;
import org.evolboot.content.domain.banner.BannerQuery;
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
}
