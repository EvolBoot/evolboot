package org.evolboot.pay.domain.releasedorder;

import org.evolboot.pay.domain.releasedorder.repository.ReleasedOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 代付订单
 *
 * @author evol
* 
 */
@Service
@Slf4j
public class ReleasedOrderListener extends ReleasedOrderSupportService {

    protected ReleasedOrderListener(ReleasedOrderRepository repository) {
        super(repository);
    }
/*

    @EventListener
    public void on(DomainEvent event) {

    }
*/

}
