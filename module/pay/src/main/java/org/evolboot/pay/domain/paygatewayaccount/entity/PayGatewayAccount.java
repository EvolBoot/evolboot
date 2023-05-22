package org.evolboot.pay.domain.paygatewayaccount.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.data.jpa.JpaAbstractEntity;
import org.evolboot.core.domain.AggregateRoot;
import org.evolboot.core.domain.IdGenerate;
import org.evolboot.core.domain.LocaleDomainPart;
import org.evolboot.pay.domain.paygatewayaccount.repository.jpa.convert.PayGatewayAccountLocaleListConverter;
import org.evolboot.shared.pay.PayGateway;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.List;


/**
 * 支付网关账户
 *
 * @author evol
 */
@Table(name = "gh_pay_gateway_account")
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
    private PayGateway payGateway;

    private String alias;

    private void generateId() {
        this.id = IdGenerate.longId();
    }

    public PayGatewayAccount(String logo,
                             String merchantId,
                             String appid,
                             String secretKey,
                             BigDecimal minimumReceipt,
                             BigDecimal maximumReceipt,
                             Boolean enable,
                             PayGateway payGateway,
                             Integer sort,
                             String walletId,
                             List<PayGatewayAccountLocale> locales,
                             String alias) {
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
        setAlias(alias);

    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setLocales(List<PayGatewayAccountLocale> locales) {
        this.locales = locales;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public void setMinimumReceipt(BigDecimal minimumReceipt) {
        this.minimumReceipt = minimumReceipt;
    }

    public void setMaximumReceipt(BigDecimal maximumReceipt) {
        this.maximumReceipt = maximumReceipt;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public void setPayGateway(PayGateway payGateway) {
        this.payGateway = payGateway;
    }

    public void setSort(Integer sort) {
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
