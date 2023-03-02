package org.evolboot.pay.domain.paymentclient.receipt;

import org.evolboot.pay.domain.shared.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 代收下单
 *
 * @author evol
 */
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ReceiptCreateRequest {


    // 内部订单ID
    private String internalOrderId;

    // 商品信息
    private String productName;

    // 支付人姓名
    private String payeeName;

    private String payeePhone;

    private String payeeEmail;

    // 支付金额
    private BigDecimal payAmount;

    private Currency currency;

    private Long payGatewayAccountId;

    private String redirectUrl;

}
