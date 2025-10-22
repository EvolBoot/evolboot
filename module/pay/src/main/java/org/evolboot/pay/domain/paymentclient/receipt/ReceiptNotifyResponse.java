package org.evolboot.pay.domain.paymentclient.receipt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.evolboot.pay.domain.payinorder.entity.PayinOrderNotifyResult;
import org.evolboot.shared.pay.PayinOrderState;

/**
 * 代收通知返回
 *
 * @author evol
 */

@AllArgsConstructor
@Getter
public class ReceiptNotifyResponse {

    private PayinOrderState state;

    private String returnText;

    private PayinOrderNotifyResult notifyResult;

}
