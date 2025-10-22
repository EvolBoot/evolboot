package org.evolboot.pay.domain.paymentclient.payout;

/**
 * 代付请求结果
 *
 * @author evol
 */
public interface PayoutRequestResult {

    boolean isOk();

    String getState();

    String getForeignOrderId();

}
