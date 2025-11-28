package org.evolboot.pay.domain.paygatewayaccount.dto;

import lombok.Getter;
import lombok.Setter;
import org.evolboot.pay.domain.paygatewayaccount.entity.PayGatewayAccountLocale;
import org.evolboot.pay.domain.paygatewayaccount.entity.PayGatewayCurrencyLimit;
import org.evolboot.shared.pay.PayGateway;

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

    // 支持的货币及其限制
    private List<PayGatewayCurrencyLimit> supportCurrencies;

    private Boolean enable = true;

    private PayGateway payGateway;

    private Integer sort;

    private String walletId;

    private String alias;

}
