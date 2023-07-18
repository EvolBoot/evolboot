package org.evolboot.pay.domain.paymentclient.receipt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.evolboot.pay.domain.receiptorder.entity.ReceiptOrderNotifyResult;
import org.evolboot.shared.pay.ReceiptOrderState;

/**
 * 代收通知返回
 *
 * @author evol
 */

@AllArgsConstructor
@Getter
public class ReceiptNotifyResponse {

    private ReceiptOrderState state;

    private String returnText;

    private ReceiptOrderNotifyResult notifyResult;

}
