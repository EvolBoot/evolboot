package org.evolboot.pay.domain.paymentclient.receipt;

/**
 * @author evol
 */
public interface ReceiptRequestResult {

    boolean isOk();

    String getStatus();

    String getPayUrl();

    String getForeignOrderId();

}
