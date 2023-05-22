package org.evolboot.system.domain.banner.repository;

import org.evolboot.core.data.BaseRepository;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.Query;
import org.evolboot.system.domain.banner.entity.Banner;

import java.util.List;
import java.util.Optional;

/**
 * banner
 *
 * @author evol
 */
public interface BannerRepository extends BaseRepository<Banner, Long> {

    Banner save(Banner banner);

    Optional<Banner> findById(Long id);


    void deleteById(Long id);

    List<Banner> findAll();


    <Q extends Query> List<Banner> findAll(Q query);

    <Q extends Query> Optional<Banner> findOne(Q query);

    <Q extends Query> long count(Q query);

    <Q extends Query> Page<Banner> page(Q query);
}
