package org.evolboot.content.domain.banner;

import org.evolboot.content.domain.banner.repository.BannerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * banner
 *
 * @author evol
 * 
 */
@Service
@Slf4j
public class BannerListener extends BannerSupportService {

    protected BannerListener(BannerRepository repository) {
        super(repository);
    }
/*

    @EventListener
    public void on(DomainEvent event) {

    }
*/

}
