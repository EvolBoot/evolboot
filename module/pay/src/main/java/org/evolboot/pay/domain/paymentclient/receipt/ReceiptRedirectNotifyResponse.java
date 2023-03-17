package org.evolboot.pay.domain.paymentclient.receipt;

import org.evolboot.pay.domain.receiptorder.ReceiptOrderNotifyResult;
import org.evolboot.shared.pay.ReceiptOrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 代收通知返回
 *
 * @author evol
 */

@AllArgsConstructor
@Getter
public class ReceiptRedirectNotifyResponse {

    private ReceiptOrderStatus status;


}
