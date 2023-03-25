package org.evolboot.pay.domain.receiptorder;

import org.evolboot.core.data.Page;
import org.evolboot.pay.domain.paymentclient.receipt.ReceiptNotifyRequest;
import org.evolboot.pay.domain.paymentclient.receipt.ReceiptRedirectNotifyRequest;
import org.evolboot.pay.domain.receiptorder.service.ReceiptOrderCreateFactory;
import org.evolboot.pay.domain.receiptorder.service.ReceiptOrderUpdateService;
import org.evolboot.shared.pay.ReceiptOrderStatus;

import java.util.List;

/**
 * 第三方代收订单
 *
 * @author evol
 */
public interface ReceiptOrderAppService {

    /**
     * 创建订单
     * @param request
     * @return
     */
    ReceiptOrder create(ReceiptOrderCreateFactory.Request request);

    List<ReceiptOrder> findAll();

    List<ReceiptOrder> findAll(ReceiptOrderQuery query);

    Page<ReceiptOrder> page(ReceiptOrderQuery query);

    ReceiptOrder findById(String id);

    <T extends ReceiptNotifyRequest> Object receiptOrderNotify(T request);

    <T extends ReceiptRedirectNotifyRequest> String getReceiptRedirectUrl(T request) ;

}
