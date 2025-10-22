package org.evolboot.pay;


import org.evolboot.core.i18n.I18NMessageHolder;

import java.math.BigDecimal;

/**
 * 国际化
 *
 * @author evol
 */
public abstract class PayI18nMessage {

    public static final String NAMESPACE = "pay";


    /**
     * 第三方代收订单
     */
    public static class PayinOrder {
        public static final String NAMESPACE = PayI18nMessage.NAMESPACE + ".payinorder";

        public static final String NOT_FOUND = NAMESPACE + ".notFound";

        public static String notFound() {
            return I18NMessageHolder.message(NOT_FOUND);
        }
    }


    /**
     * 支付网关账户
     */
    public static class PayGatewayAccount {
        public static final String NAMESPACE = PayI18nMessage.NAMESPACE + ".paygatewayaccount";

        public static final String NOT_FOUND = NAMESPACE + ".notFound";
        public static final String theMinimumRechargeAmountIs = NAMESPACE + ".theMinimumRechargeAmountIs";

        public static String notFound() {
            return I18NMessageHolder.message(NOT_FOUND);
        }
    }

    /**
     * 支付网关账户
     */
    public static class PaymentClient {
        public static final String NAMESPACE = PayI18nMessage.NAMESPACE + ".paymentclient";

        public static final String NOT_FOUND = NAMESPACE + ".notFound";
        public static final String theMinimumRechargeAmountIs = NAMESPACE + ".theMinimumRechargeAmountIs";
        public static final String thePaymentGatewayDoesNotExist = NAMESPACE + ".thePaymentGatewayDoesNotExist";

        public static String notFound() {
            return I18NMessageHolder.message(NOT_FOUND);
        }

        public static String theMinimumRechargeAmountIs(BigDecimal amount) {
            return I18NMessageHolder.message(theMinimumRechargeAmountIs, amount);
        }

        public static String thePaymentGatewayDoesNotExist() {
            return I18NMessageHolder.message(thePaymentGatewayDoesNotExist);
        }
    }


    /**
     * 代付订单
     */
    public static class ReleasedOrder {
        public static final String NAMESPACE = PayI18nMessage.NAMESPACE + ".releasedorder";

        public static final String NOT_FOUND = NAMESPACE + ".notFound";

        public static String notFound() {
            return I18NMessageHolder.message(NOT_FOUND);
        }
    }

    /**
     * 支付网关账户
     */
    public static class TestProduct {
        public static final String NAMESPACE = PayI18nMessage.NAMESPACE + ".testproduct";

        public static final String NOT_FOUND = NAMESPACE + ".notFound";

    }
}
