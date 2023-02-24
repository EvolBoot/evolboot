package org.evolboot.pay.domain.paymentclient.released;

import org.evolboot.shared.pay.ReleasedOrderType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * 代付订单请求
 *
 * @author evol
 */
@Getter
@AllArgsConstructor
@ToString
public class ReleasedOrderRequest {

    // 内部订单ID
    private String internalOrderId;

    // 金额数量
    private BigDecimal amount;

    // 下发类型
    private ReleasedOrderType type;

    // 客户姓名
    private String bankBankCardholderName;

    // 银行卡卡号
    private String bankNo;

    // 银行代码
    private String bankCode;

    // 代付网关
    private Long payGatewayAccountId;

    private String customMobile;

    private String customEmail;


}
