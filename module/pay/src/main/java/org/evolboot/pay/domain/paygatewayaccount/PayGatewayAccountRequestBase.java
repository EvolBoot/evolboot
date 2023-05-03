package org.evolboot.pay.domain.paygatewayaccount;

import org.evolboot.shared.pay.PayGateway;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.util.List;

/**
 * 支付网关账户
 *
 * @author evol
 */
@Setter
@Getter
public abstract class PayGatewayAccountRequestBase {

    private String logo;

    private List<PayGatewayAccountLocale> locales;

    // 账户号
    private String merchantId;

    private String appid;

    // 用来加密的
    private String secretKey;

    private BigDecimal minimumReceipt;

    private BigDecimal maximumReceipt;

    private Boolean enable = true;

    private PayGateway payGateway;

    private Integer sort;

    private String walletId;

    private String alias;

}
