package org.evolboot.pay.domain.paymentclient.receipt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.evolboot.pay.domain.receiptorder.entity.ReceiptOrderRequestResult;

/**
 * 代收下单返回
 *
 * @author evol
 */
@AllArgsConstructor
@Getter
public class ReceiptCreateResponse {

    private boolean ok;

    private String receiptOrderId;

    private String payUrl;

    private ReceiptOrderRequestResult requestResult;

}
