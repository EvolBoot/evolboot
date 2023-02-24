package org.evolboot.pay.domain.paymentclient.receipt;

import org.evolboot.pay.domain.receiptorder.ReceiptOrderRequestResult;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 代收下单返回
 *
 * @author evol
 */
@AllArgsConstructor
@Getter
public class ReceiptOrderResponse {

    private boolean ok;

    private String receiptOrderId;

    private String payUrl;

    private ReceiptOrderRequestResult requestResult;

}
