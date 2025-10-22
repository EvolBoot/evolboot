package org.evolboot.pay.domain.paymentclient.payin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.evolboot.pay.domain.payinorder.entity.PayinOrderRequestResult;

/**
 * 代收下单返回
 *
 * @author evol
 */
@AllArgsConstructor
@Getter
public class PayinCreateResponse {

    private boolean ok;

    private String payinOrderId;

    private String payUrl;

    private PayinOrderRequestResult requestResult;

}
