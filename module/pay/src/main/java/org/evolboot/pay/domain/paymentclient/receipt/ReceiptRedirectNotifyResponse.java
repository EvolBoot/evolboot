package org.evolboot.pay.domain.paymentclient.receipt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.evolboot.shared.pay.ReceiptOrderStatus;

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
