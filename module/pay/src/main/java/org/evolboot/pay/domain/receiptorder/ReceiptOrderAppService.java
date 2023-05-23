package org.evolboot.pay.domain.receiptorder;

import org.evolboot.core.data.Page;
import org.evolboot.pay.domain.paymentclient.receipt.ReceiptNotifyRequest;
import org.evolboot.pay.domain.paymentclient.receipt.ReceiptRedirectNotifyRequest;
import org.evolboot.pay.domain.receiptorder.entity.ReceiptOrder;
import org.evolboot.pay.domain.receiptorder.service.ReceiptOrderCreateFactory;
import org.evolboot.pay.domain.receiptorder.service.ReceiptOrderQuery;

import java.util.List;
import java.util.Optional;

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

    List<ReceiptOrder> findAll();

    List<ReceiptOrder> findAll(ReceiptOrderQuery query);

    Page<ReceiptOrder> page(ReceiptOrderQuery query);

    ReceiptOrder findById(String id);

    <T extends ReceiptNotifyRequest> Object receiptOrderNotify(T request);

    <T extends ReceiptRedirectNotifyRequest> String getReceiptRedirectUrl(T request);


    /**
     * 根据条件查询单个
     *
     * @param query
     * @return
     */
    Optional<ReceiptOrder> findOne(ReceiptOrderQuery query);


}
