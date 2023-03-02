package org.evolboot.pay.domain.paymentclient.released;

import org.evolboot.shared.pay.PayGateway;

import java.math.BigDecimal;

/**
 * 代付通知
 *
 * @author evol
 */
public interface ReleasedNotifyRequest {

    String getReleasedOrderId();

    String getForeignOrderId();

    String getStatus();

    String getNotifyParamsText();

    BigDecimal getAmount();

    BigDecimal getPoundage();

    boolean isOk();


    boolean checkSign(String secretKey);
}
