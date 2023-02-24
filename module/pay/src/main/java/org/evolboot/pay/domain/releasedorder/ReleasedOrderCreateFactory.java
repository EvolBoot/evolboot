package org.evolboot.pay.domain.releasedorder;

import org.evolboot.shared.pay.PayGateway;
import org.evolboot.shared.pay.ReleasedOrderStatus;
import org.evolboot.shared.pay.ReleasedOrderType;
import org.evolboot.pay.domain.releasedorder.repository.ReleasedOrderRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 代付订单
 *
 * @author evol
* 
 */
@Slf4j
@Service
public class ReleasedOrderCreateFactory extends ReleasedOrderSupportService {
    protected ReleasedOrderCreateFactory(ReleasedOrderRepository repository) {
        super(repository);
    }

    public ReleasedOrder execute(Request request) {
        ReleasedOrder releasedOrder = new ReleasedOrder(
                request.id,
                request.type,
                request.internalOrderId,
                request.amount,
                request.customName,
                request.customMobile,
                request.customEmail,
                request.bankBankCardholderName,
                request.bankCode,
                request.bankNo,
                request.payGatewayAccountId,
                request.payGateway,
                request.poundage,
                request.foreignOrderId,
                request.requestResult,
                request.status
        );
        repository.save(releasedOrder);
        return releasedOrder;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Request {
        private String id;
        private ReleasedOrderType type;
        private String internalOrderId;
        private BigDecimal amount;
        private String customName;
        private String customMobile;
        private String customEmail;
        private String bankBankCardholderName;
        private String bankCode;
        private String bankNo;
        private Long payGatewayAccountId;
        private PayGateway payGateway;
        private BigDecimal poundage;
        private String foreignOrderId;
        private ReleasedOrderRequestResult requestResult;
        private ReleasedOrderStatus status;

    }

}
