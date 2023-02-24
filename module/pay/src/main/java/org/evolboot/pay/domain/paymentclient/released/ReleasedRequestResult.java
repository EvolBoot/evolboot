package org.evolboot.pay.domain.paymentclient.released;

/**
 * 代付请求结果
 *
 * @author evol
 */
public interface ReleasedRequestResult {

    boolean isOk();

    String getStatus();

    String getForeignOrderId();

}
