package org.evolboot.pay.domain.paymentclient.receipt;

/**
 * @author evol
 */
public interface ReceiptFacadeService {


    /**
     * 代收
     *
     * @param request
     * @return
     */
    ReceiptOrderResponse createReceiptOrder(ReceiptOrderRequest request);


    /**
     * 代收通知
     *
     * @param parameters
     * @param <T>
     * @return
     */
    <T extends ReceiptNotifyRequest> Object receiptOrderNotify(T parameters);


}
