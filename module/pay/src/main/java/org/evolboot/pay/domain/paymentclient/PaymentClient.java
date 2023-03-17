package org.evolboot.pay.domain.paymentclient;


import org.evolboot.shared.pay.Currency;
import org.evolboot.shared.pay.PayGateway;

/**
 * 指定网关
 *
 * @author evol
 */
public interface PaymentClient {

    PayGateway getPayGateway();

    boolean supportCurrency(Currency currency);


}
