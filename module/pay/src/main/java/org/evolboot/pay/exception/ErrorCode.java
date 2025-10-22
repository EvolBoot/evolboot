package org.evolboot.pay.exception;

/**
 * @author evol
 */
public interface ErrorCode {

    /**
     * 第三方代付订单错误
     */
    Integer PAYOUT_ORDER_ERROR = 10001;

    /**
     * 第三方代收订单错误
     */
    Integer PAYIN_ORDER_ERROR = 10002;


    /**
     * 传入参数错误
     */
    Integer INCOMING_PARAMETER_ERROR = 10003;

    /**
     * 参数为空
     */
    Integer PARAMETER_IS_NULL = 10004;

    /**
     * 重复订单
     */
    Integer REPEAT_ORDERS = 10005;

    /**
     * 订单检查错误
     */
    Integer ORDER_CHECK_ERROR = 10006;

    /**
     * 代付不支持该收款组织
     */
    Integer DOT_SUPPORT_ORG = 10007;

    /**
     * 网关找不到
     */
    Integer GATEWAY_NOT_FOUND = 10008;


    /**
     * 不支持该货币
     */
    Integer DOT_SUPPORT_CURRENCY = 10009;

}
