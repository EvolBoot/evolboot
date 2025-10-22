package org.evolboot.pay.exception;


import org.evolboot.core.exception.ExtendErrorCodeException;
import org.evolboot.shared.pay.Currency;
import org.evolboot.shared.pay.PayoutOrderOrgType;

/**
 * @author evol
 */
public class PayException extends ExtendErrorCodeException {

    public final static PayException PAYOUT_ORDER_ERROR = new PayException(ErrorCode.PAYOUT_ORDER_ERROR, "Payout fail,Please contact customer service.");
    public final static PayException PAYIN_ORDER_ERROR = new PayException(ErrorCode.PAYIN_ORDER_ERROR, "Payin fail, Please contact customer service.");
    public final static PayException GATEWAY_NOT_FOUND = new PayException(ErrorCode.GATEWAY_NOT_FOUND, "Gateway cannot be found");

    public static PayException ofParameterIsNull(String paramKey) {
        return new PayException(ErrorCode.PARAMETER_IS_NULL, "[" + paramKey + "] Cannot be null");
    }

    public static PayException dotSupportOrgType(String gatewayAlias, PayoutOrderOrgType orderOrgType) {
        return new PayException(ErrorCode.DOT_SUPPORT_ORG, gatewayAlias + " Do not support the organization(" + orderOrgType + ")");
    }


    public static PayException dotSupportCurrency(String gatewayAlias, Currency currency) {
        return new PayException(ErrorCode.DOT_SUPPORT_CURRENCY, gatewayAlias + " Do not support the currency(" + currency + ")");
    }


    public static PayException ofRepeatOrders(String orderId) {
        return new PayException(ErrorCode.REPEAT_ORDERS, "[" + orderId + "] Repeat orders");
    }


    public static PayException ofOrderCheckError(String message) {
        return new PayException(ErrorCode.ORDER_CHECK_ERROR, message);
    }

    public static PayException incomingParameterError(String orderId) {
        return new PayException(ErrorCode.INCOMING_PARAMETER_ERROR, "(" + orderId + ")");
    }


    private final int errorCode;

    @Override
    public int getErrorCode() {
        return errorCode;
    }

    public PayException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }


}
