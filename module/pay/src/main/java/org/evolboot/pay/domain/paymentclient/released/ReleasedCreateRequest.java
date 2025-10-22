package org.evolboot.pay.domain.paymentclient.released;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.evolboot.shared.pay.Currency;
import org.evolboot.shared.pay.PayoutOrderOrgType;

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


    private Currency currency;

    // 金额数量
    private BigDecimal amount;

    // 客户姓名
    private String payeeName;

    private String payeePhone;

    private String payeeEmail;

    // 银行代码
    private String bankCode;

    // 银行卡卡号
    private String bankNo;

    //ifsc 编号
    private String ifscCode;

    // ifsc 卡号
    private String ifscCardNo;

    private PayoutOrderOrgType orgType;


}
