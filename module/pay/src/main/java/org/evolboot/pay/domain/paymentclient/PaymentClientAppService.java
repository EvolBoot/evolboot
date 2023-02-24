package org.evolboot.pay.domain.paymentclient;

import org.evolboot.pay.domain.paymentclient.receipt.ReceiptNotifyRequest;
import org.evolboot.pay.domain.paymentclient.receipt.ReceiptOrderRequest;
import org.evolboot.pay.domain.paymentclient.receipt.ReceiptOrderResponse;
import org.evolboot.pay.domain.paymentclient.released.ReleasedNotifyRequest;
import org.evolboot.pay.domain.paymentclient.released.ReleasedOrderRequest;
import org.evolboot.pay.domain.paymentclient.released.ReleasedOrderResponse;

/**
 * 支付客户端（对外）
 *
 * @author evol
 */
public interface PaymentClientAppService {

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

    /**
     * 下发(代付)订单
     *
     * @param request
     * @return
     */
    ReleasedOrderResponse createReleasedOrder(ReleasedOrderRequest request);


    /**
     * 下发(代付)通知
     *
     * @param parameters
     * @param <T>
     * @return
     */
    <T extends ReleasedNotifyRequest> Object releasedOrderNotify(T parameters);
}
