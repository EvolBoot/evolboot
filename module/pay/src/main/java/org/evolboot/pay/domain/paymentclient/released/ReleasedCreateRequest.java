package org.evolboot.pay.domain.paymentclient.released;

import org.evolboot.shared.pay.Currency;
import org.evolboot.shared.pay.ReleasedOrderOperator;
import org.evolboot.shared.pay.ReleasedOrderOrgType;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
public class ReleasedCreateRequest {

    // 内部订单ID
    private String internalOrderId;

    private Currency  currency;

    // 金额数量
    private BigDecimal amount;

    // 客户姓名
    private String payeeName;

    private String payeePhone;

    private String payeeEmail;

    // 代付网关
    private Long payGatewayAccountId;

    // 银行代码
    private String bankCode;

    // 银行卡卡号
    private String bankNo;

    //ifsc 编号
    private String ifscCode;

    // ifsc 卡号
    private String ifscCardNo;

    private ReleasedOrderOrgType orgType;

}