package org.evolboot.locale.domain.region.repository;

import org.evolboot.core.data.Page;
import org.evolboot.locale.domain.region.Region;
import org.evolboot.locale.domain.region.RegionQuery;

import java.util.List;
import java.util.Optional;

/**
 * @author evol
 */
public interface RegionRepository {

    Region save(Region region);

    Optional<Region> findById(Long id);

    Page<Region> page(RegionQuery query);

    void deleteById(Long id);

    List<Region> findAll();

    List<Region> findAllByEnableIsTrue();
}
