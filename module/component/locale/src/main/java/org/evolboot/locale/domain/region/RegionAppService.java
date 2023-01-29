package org.evolboot.locale.domain.region;

import org.evolboot.core.data.Page;

import java.util.List;

/**
 * @author evol
 */
public interface RegionAppService {

    Long create(RegionCreateFactory.Request request);

    void update(Long id, RegionUpdateService.Request request);

    void delete(Long id);

    List<Region> list();

    List<Region> findAllByEnableIsTrue();

    Page<Region> page(RegionQuery query);

    Region findById(Long id);

    void enable(Long id);

    void disable(Long id);
}
