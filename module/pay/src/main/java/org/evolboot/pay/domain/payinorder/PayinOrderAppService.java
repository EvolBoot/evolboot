package org.evolboot.pay.domain.payinorder;

import org.evolboot.pay.domain.paymentclient.receipt.ReceiptNotifyRequest;
import org.evolboot.pay.domain.paymentclient.receipt.ReceiptRedirectNotifyRequest;
import org.evolboot.pay.domain.payinorder.entity.PayinOrder;
import org.evolboot.pay.domain.payinorder.service.PayinOrderCreateFactory;

/**
 * 第三方代收订单
 *
 * @author evol
 */
public interface PayinOrderAppService {

    /**
     * 创建订单
     *
     * @param request
     * @return
     */
    PayinOrder create(PayinOrderCreateFactory.Request request);


    <T extends ReceiptNotifyRequest> Object receiptOrderNotify(T request);

    <T extends ReceiptRedirectNotifyRequest> String getReceiptRedirectUrl(T request);


}
