package org.evolboot.locale.domain.region;

import org.evolboot.locale.domain.region.repository.RegionRepository;
import org.springframework.stereotype.Service;

/**
 * @author evol
 */
@Service
public class RegionCreateFactory extends RegionSupportService {
    protected RegionCreateFactory(RegionRepository repository) {
        super(repository);
    }

    public Long execute(Request request) {
        Region region = new Region(
                request.getName(),
                request.getShortName(),
                request.getMobilePrefix(),
                request.getFlag(),
                request.getRemark(),
                request.getSort(),
                request.getEnable()
        );
        repository.save(region);
        return region.getId();
    }

    public static class Request extends RegionRequestBase {
    }

}
