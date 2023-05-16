package org.evolboot.pay.domain.paymentclient.receipt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.evolboot.pay.domain.receiptorder.ReceiptOrderNotifyResult;
import org.evolboot.shared.pay.ReceiptOrderStatus;

/**
 * 代收通知返回
 *
 * @author evol
 */

@AllArgsConstructor
@Getter
public class ReceiptNotifyResponse {

    private ReceiptOrderStatus status;

    private String returnText;

    private ReceiptOrderNotifyResult notifyResult;

}
