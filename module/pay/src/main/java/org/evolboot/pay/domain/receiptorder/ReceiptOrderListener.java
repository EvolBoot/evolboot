package org.evolboot.pay.domain.receiptorder;

import org.evolboot.pay.domain.receiptorder.repository.ReceiptOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 第三方代收订单
 *
 * @author evol
 */
@Service
@Slf4j
public class ReceiptOrderListener extends ReceiptOrderSupportService {

    protected ReceiptOrderListener(ReceiptOrderRepository repository) {
        super(repository);
    }
/*

    @EventListener
    public void on(DomainEvent event) {

    }
*/

}
