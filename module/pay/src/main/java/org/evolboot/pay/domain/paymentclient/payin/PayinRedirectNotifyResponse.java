package org.evolboot.pay.domain.paymentclient.payin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.evolboot.shared.pay.PayinOrderState;

/**
 * 代收通知返回
 *
 * @author evol
 */

@AllArgsConstructor
@Getter
public class PayinRedirectNotifyResponse {

    private PayinOrderState state;


}
