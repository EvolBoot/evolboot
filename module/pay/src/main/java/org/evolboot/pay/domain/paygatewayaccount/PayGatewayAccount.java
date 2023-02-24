package org.evolboot.pay.domain.paygatewayaccount;

import org.evolboot.core.data.jpa.JpaAbstractEntity;
import org.evolboot.core.domain.AggregateRoot;
import org.evolboot.core.domain.IdGenerate;
import org.evolboot.core.domain.LocaleDomainPart;
import org.evolboot.shared.pay.PayGateway;
import org.evolboot.pay.domain.paygatewayaccount.repository.jpa.convert.PayGatewayAccountLocaleListConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * 支付网关账户
 *
 * @author evol
 */
@Table(name = "evoltb_pay_gateway_account")
@Getter
@Slf4j
@NoArgsConstructor
@Entity
public class PayGatewayAccount extends JpaAbstractEntity<Long> implements AggregateRoot<PayGatewayAccount>, LocaleDomainPart<PayGatewayAccountLocale> {

    @Id
    private Long id;

    // Logo
    private String logo;

    // 多语言(账号名称）
    @Convert(converter = PayGatewayAccountLocaleListConverter.class)
    private List<PayGatewayAccountLocale> locales;

    // 商户ID
    private String merchantId;

    // APP ID
    private String appid;

    // 秘钥
    private String secretKey;

    // 最少支付
    private BigDecimal minimumReceipt;

    // 最多支付
    private BigDecimal maximumReceipt;

    //启用状态
    private Boolean enable = true;

    private String walletId;

    private Integer sort = 0;

    // 网关
    @Enumerated(EnumType.STRING)
    private PayGateway payGateway;


    private void generateId() {
        this.id = IdGenerate.longId();
    }

    public PayGatewayAccount(String logo, String merchantId, String appid, String secretKey, BigDecimal minimumReceipt, BigDecimal maximumReceipt, Boolean enable, PayGateway payGateway, Integer sort, String walletId, List<PayGatewayAccountLocale> locales) {
        generateId();
        setLogo(logo);
        setMerchantId(merchantId);
        setAppid(appid);
        setSecretKey(secretKey);
        setMinimumReceipt(minimumReceipt);
        setMaximumReceipt(maximumReceipt);
        setEnable(enable);
        setPayGateway(payGateway);
        setLocales(locales);
        setSort(sort);
        setWalletId(walletId);
    }

    void setWalletId(String walletId) {
        this.walletId = walletId;
    }

    void setLogo(String logo) {
        this.logo = logo;
    }

    void setLocales(List<PayGatewayAccountLocale> locales) {
        this.locales = locales;
    }

    void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    void setAppid(String appid) {
        this.appid = appid;
    }

    void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    void setMinimumReceipt(BigDecimal minimumReceipt) {
        this.minimumReceipt = minimumReceipt;
    }

    void setMaximumReceipt(BigDecimal maximumReceipt) {
        this.maximumReceipt = maximumReceipt;
    }

    void setEnable(Boolean enable) {
        this.enable = enable;
    }

    void setPayGateway(PayGateway payGateway) {
        this.payGateway = payGateway;
    }

    void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public Long id() {
        return id;
    }

    @Override
    public PayGatewayAccount root() {
        return this;
    }
}
