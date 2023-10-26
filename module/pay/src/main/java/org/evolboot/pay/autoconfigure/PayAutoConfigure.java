package org.evolboot.pay.autoconfigure;

import com.google.common.collect.Maps;
import org.evolboot.pay.domain.paymentclient.PaymentClient;
import org.evolboot.pay.domain.paymentclient.gateway.huanqiupay.HuanQiuPayConfig;
import org.evolboot.pay.domain.paymentclient.receipt.ReceiptClient;
import org.evolboot.pay.domain.paymentclient.released.ReleasedClient;
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
    public Map<PayGateway, ReceiptClient> receiptClientMap(List<PaymentClient> paymentClients) {
        Map<PayGateway, ReceiptClient> receiptClientMap = Maps.newHashMap();
        paymentClients.forEach(paymentClient -> {
            if (paymentClient instanceof ReceiptClient) {
                receiptClientMap.put(paymentClient.getPayGateway(), (ReceiptClient) paymentClient);
            }
        });
        return receiptClientMap;
    }

    /**
     * 代付
     *
     * @param paymentClients
     * @return
     */
    @Bean
    public Map<PayGateway, ReleasedClient> releasedClientMap(List<PaymentClient> paymentClients) {
        Map<PayGateway, ReleasedClient> releasedClientMap = Maps.newHashMap();
        paymentClients.forEach(paymentClient -> {
            if (paymentClient instanceof ReleasedClient) {
                releasedClientMap.put(paymentClient.getPayGateway(), (ReleasedClient) paymentClient);
            }
        });
        return releasedClientMap;
    }


    @Bean
    public HuanQiuPayConfig huanQiuPayConfig(PayConfig config) {
        return new HuanQiuPayConfig(config.getDomain());
    }


}
