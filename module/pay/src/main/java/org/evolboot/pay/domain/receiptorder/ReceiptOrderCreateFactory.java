package org.evolboot.pay.domain.receiptorder;

import org.evolboot.shared.pay.PayGateway;
import org.evolboot.pay.domain.receiptorder.repository.ReceiptOrderRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 第三方代收订单
 *
 * @author evol
 */
@Slf4j
@Service
public class ReceiptOrderCreateFactory extends ReceiptOrderSupportService {
    protected ReceiptOrderCreateFactory(ReceiptOrderRepository repository) {
        super(repository);
    }

    public ReceiptOrder execute(Request request) {
        ReceiptOrder receiptOrder = new ReceiptOrder(
                request.getReceiptOrderId(),
                request.getInternalOrderId(),
                request.getProductName(),
                request.getPayeeName(),
                request.getPayeePhone(),
                request.getPayeeEmail(),
                request.getPayGatewayAccountId(),
                request.getPayAmount(),
                request.getPayGateway(),
                request.getCallbackUrl(),
                request.getResult()
        );
        repository.save(receiptOrder);
        return receiptOrder;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Request extends ReceiptOrderRequestBase {
        private String receiptOrderId;
        private String internalOrderId;
        private String productName;
        private String payeeName;
        private String payeePhone;
        private String payeeEmail;
        private Long payGatewayAccountId;
        private BigDecimal payAmount;
        private PayGateway payGateway;
        private String callbackUrl;
        private ReceiptOrderRequestResult result;
    }

}
