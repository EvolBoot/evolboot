package org.evolboot.pay.domain.paymentclient.receipt;

import org.evolboot.pay.domain.paygatewayaccount.PayGatewayAccount;
import org.evolboot.shared.pay.ReceiptOrderStatus;

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
    ReceiptCreateResponse createReceiptOrder(ReceiptCreateRequest request);


    /**
     * 代收通知
     *
     * @param request
     * @param <T>
     * @return
     */
    <T extends ReceiptNotifyRequest> Object receiptOrderNotify(T request);

    /**
     * 获取前端跳转地址
     * @param request
     * @return
     */
    <T extends ReceiptRedirectNotifyRequest> String getReceiptRedirectUrl(T request);


}
