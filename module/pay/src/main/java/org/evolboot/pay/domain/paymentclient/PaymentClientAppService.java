package org.evolboot.pay.domain.paymentclient;

import org.evolboot.pay.domain.paymentclient.receipt.ReceiptNotifyRequest;
import org.evolboot.pay.domain.paymentclient.receipt.ReceiptCreateRequest;
import org.evolboot.pay.domain.paymentclient.receipt.ReceiptCreateResponse;
import org.evolboot.pay.domain.paymentclient.receipt.ReceiptRedirectNotifyRequest;
import org.evolboot.pay.domain.paymentclient.released.ReleasedNotifyRequest;
import org.evolboot.pay.domain.paymentclient.released.ReleasedCreateRequest;
import org.evolboot.pay.domain.paymentclient.released.ReleasedCreateResponse;
import org.evolboot.shared.pay.ReceiptOrderStatus;

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
    ReceiptCreateResponse createReceiptOrder(ReceiptCreateRequest request);


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
    ReleasedCreateResponse createReleasedOrder(ReleasedCreateRequest request);


    /**
     * 下发(代付)通知
     *
     * @param parameters
     * @param <T>
     * @return
     */
    <T extends ReleasedNotifyRequest> Object releasedOrderNotify(T parameters);


    /**
     * 获取前端跳转地址
     *
     * @param request
     * @return
     */
    <T extends ReceiptRedirectNotifyRequest> String getReceiptRedirectUrl(T request);
}
