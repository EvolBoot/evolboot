package org.evolboot.pay.domain.receiptorder;

import org.evolboot.core.data.Page;
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

    ReceiptOrder create(ReceiptOrderCreateFactory.Request request);

    void update(String id, ReceiptOrderUpdateService.Request request);

    void delete(String id);

    List<ReceiptOrder> findAll();

    List<ReceiptOrder> findAll(ReceiptOrderQuery query);

    Page<ReceiptOrder> page(ReceiptOrderQuery query);

    ReceiptOrder findById(String id);

    void success(String id, ReceiptOrderNotifyResult receiptOrderNotifyResult);

    void fail(String id, ReceiptOrderNotifyResult receiptOrderNotifyResult);

    String getRedirectUrl(String id, ReceiptOrderStatus status);

}
