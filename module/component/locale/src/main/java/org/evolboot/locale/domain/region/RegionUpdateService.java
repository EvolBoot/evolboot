package org.evolboot.locale.domain.region;

import org.evolboot.core.util.ExtendObjects;
import org.evolboot.locale.domain.region.repository.RegionRepository;
import org.springframework.stereotype.Service;

/**
 * @author evol
 */
@Service
public class RegionUpdateService extends RegionSupportService {
    protected RegionUpdateService(RegionRepository repository) {
        super(repository);
    }

    public void execute(Long id, Request request) {
        Region region = findById(id);
        if (ExtendObjects.isNotBlank(request.getName())) {
            region.setName(request.getName());
        }
        if (ExtendObjects.isNotBlank(request.getShortName())) {
            region.setShortName(request.getShortName());
        }
        if (ExtendObjects.isNotBlank(request.getMobilePrefix())) {
            region.setMobilePrefix(request.getMobilePrefix());
        }
        if (ExtendObjects.isNotBlank(request.getFlag())) {
            region.setFlag(request.getFlag());
        }
        if (ExtendObjects.isNotBlank(request.getRemark())) {
            region.setRemark(request.getRemark());
        }
        if (ExtendObjects.nonNull(request.getSort())) {
            region.setSort(request.getSort());
        }
        if (ExtendObjects.nonNull(request.getEnable())) {
            region.setEnable(request.getEnable());
        }
        repository.save(region);
    }

    public static class Request extends RegionRequestBase {

    }

}
