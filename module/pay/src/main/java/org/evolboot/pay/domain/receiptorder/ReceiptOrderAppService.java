package org.evolboot.pay.domain.receiptorder;

import org.evolboot.pay.domain.paymentclient.receipt.ReceiptNotifyRequest;
import org.evolboot.pay.domain.paymentclient.receipt.ReceiptRedirectNotifyRequest;
import org.evolboot.pay.domain.receiptorder.entity.ReceiptOrder;
import org.evolboot.pay.domain.receiptorder.service.ReceiptOrderCreateFactory;

/**
 * 第三方代收订单
 *
 * @author evol
 */
public interface ReceiptOrderAppService {

    /**
     * 创建订单
     *
     * @param request
     * @return
     */
    ReceiptOrder create(ReceiptOrderCreateFactory.Request request);


    <T extends ReceiptNotifyRequest> Object receiptOrderNotify(T request);

    <T extends ReceiptRedirectNotifyRequest> String getReceiptRedirectUrl(T request);


}
