package org.evolboot.pay.autoconfigure;

import com.google.common.collect.Maps;
import org.evolboot.pay.domain.paymentclient.PaymentClient;
import org.evolboot.pay.domain.paymentclient.gateway.huanqiupay.HuanQiuPayConfig;
import org.evolboot.pay.domain.paymentclient.gateway.nowpayments.NowPaymentsConfig;
import org.evolboot.pay.domain.paymentclient.payin.PayinClient;
import org.evolboot.pay.domain.paymentclient.payout.PayoutClient;
import org.evolboot.pay.domain.shared.PayConfig;
import org.evolboot.shared.pay.PayGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

/**
 * @author evol
 */
@Configuration
public class PayAutoConfigure {


    /**
     * 代收
     *
     * @param paymentClients
     * @return
     */
    @Bean
    public Map<PayGateway, PayinClient> payinClientMap(List<PaymentClient> paymentClients) {
        Map<PayGateway, PayinClient> payinClientMap = Maps.newHashMap();
        paymentClients.forEach(paymentClient -> {
            if (paymentClient instanceof PayinClient) {
                payinClientMap.put(paymentClient.getPayGateway(), (PayinClient) paymentClient);
            }
        });
        return payinClientMap;
    }

    /**
     * 代付
     *
     * @param paymentClients
     * @return
     */
    @Bean
    public Map<PayGateway, PayoutClient> payoutClientMap(List<PaymentClient> paymentClients) {
        Map<PayGateway, PayoutClient> payoutClientMap = Maps.newHashMap();
        paymentClients.forEach(paymentClient -> {
            if (paymentClient instanceof PayoutClient) {
                payoutClientMap.put(paymentClient.getPayGateway(), (PayoutClient) paymentClient);
            }
        });
        return payoutClientMap;
    }


    @Bean
    public HuanQiuPayConfig huanQiuPayConfig(PayConfig config) {
        return new HuanQiuPayConfig(config.getNotifyDomain());
    }

    /**
     * NOWPayments 配置
     */
    @Bean
    public NowPaymentsConfig nowPaymentsConfig(PayConfig config) {
        return new NowPaymentsConfig(
            config.getNotifyDomain(),
            config.getNowPaymentsApiKey(),
            config.getNowPaymentsIpnSecret(),
            config.isNowPaymentsSandbox()
        );
    }


}
