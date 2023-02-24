package org.evolboot.pay.domain.paymentclient;

import org.evolboot.pay.domain.paymentclient.receipt.ReceiptFacadeService;
import org.evolboot.pay.domain.paymentclient.receipt.ReceiptNotifyRequest;
import org.evolboot.pay.domain.paymentclient.receipt.ReceiptOrderRequest;
import org.evolboot.pay.domain.paymentclient.receipt.ReceiptOrderResponse;
import org.evolboot.pay.domain.paymentclient.released.ReleasedFacadeService;
import org.evolboot.pay.domain.paymentclient.released.ReleasedNotifyRequest;
import org.evolboot.pay.domain.paymentclient.released.ReleasedOrderRequest;
import org.evolboot.pay.domain.paymentclient.released.ReleasedOrderResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author evol
 */
@Service
@Slf4j
public class DefaultPaymentClientAppService implements PaymentClientAppService {

    private final ReceiptFacadeService receiptFacadeService;
    private final ReleasedFacadeService releasedFacadeService;

    public DefaultPaymentClientAppService(ReceiptFacadeService receiptFacadeService, ReleasedFacadeService releasedFacadeService) {
        this.receiptFacadeService = receiptFacadeService;
        this.releasedFacadeService = releasedFacadeService;
    }

    @Override
    @Transactional
    public ReceiptOrderResponse createReceiptOrder(ReceiptOrderRequest request) {
        return receiptFacadeService.createReceiptOrder(request);
    }

    @Override
    @Transactional
    public <T extends ReceiptNotifyRequest> Object receiptOrderNotify(T request) {
        return receiptFacadeService.receiptOrderNotify(request);
    }

    @Override
    @Transactional
    public ReleasedOrderResponse createReleasedOrder(ReleasedOrderRequest request) {
        return releasedFacadeService.createReleasedOrder(request);
    }

    @Override
    @Transactional
    public <T extends ReleasedNotifyRequest> Object releasedOrderNotify(T request) {
        return releasedFacadeService.releasedOrderNotify(request);
    }


}
