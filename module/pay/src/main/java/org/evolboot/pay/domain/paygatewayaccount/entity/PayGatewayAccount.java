package org.evolboot.pay.domain.paygatewayaccount.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.data.jpa.JpaAbstractEntity;
import org.evolboot.core.entity.AggregateRoot;
import org.evolboot.core.entity.IdGenerate;
import org.evolboot.core.entity.LocaleDomainPart;
import org.evolboot.pay.domain.paygatewayaccount.repository.jpa.convert.PayGatewayAccountLocaleListConverter;
import org.evolboot.pay.domain.paygatewayaccount.repository.jpa.convert.PayGatewayCurrencyLimitConverter;
import org.evolboot.shared.pay.Currency;
import org.evolboot.shared.pay.PayGateway;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


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
@Schema(title = "支付网关账户")
public class PayGatewayAccount extends JpaAbstractEntity<Long> implements AggregateRoot<PayGatewayAccount>, LocaleDomainPart<PayGatewayAccountLocale> {

    @Id
    private Long id;

    // Logo
    @Schema(title = "Logo")
    private String logo;

    // 多语言(账号名称）
    @Schema(title = "多语言(账号名称）")
    @Convert(converter = PayGatewayAccountLocaleListConverter.class)
    private List<PayGatewayAccountLocale> locales;

    // 商户ID
    @Schema(title = "商户ID")
    private String merchantId;

    // APP ID
    @Schema(title = "APP ID")
    private String appid;

    // 秘钥
    @Schema(title = "秘钥")
    private String secretKey;

    // 支持的货币及其限制
    @Schema(title = "支持的货币及其限制")
    @Convert(converter = PayGatewayCurrencyLimitConverter.class)
    private List<PayGatewayCurrencyLimit> supportCurrencies;

    //启用状态
    @Schema(title = "启用状态")
    private Boolean enable = true;

    @Schema(title = "钱包ID")
    private String walletId;

    @Schema(title = "排序")
    private Integer sort = 0;

    // 网关
    @Schema(title = "网关")

    @Enumerated(EnumType.STRING)
    private PayGateway payGateway;

    @Schema(title = "别名")
    private String alias;

    private void generateId() {
        this.id = IdGenerate.longId();
    }

    public PayGatewayAccount(String logo,
                             String merchantId,
                             String appid,
                             String secretKey,
                             List<PayGatewayCurrencyLimit> supportCurrencies,
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
        setSupportCurrencies(supportCurrencies);
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

    public void setSupportCurrencies(List<PayGatewayCurrencyLimit> supportCurrencies) {
        this.supportCurrencies = supportCurrencies;
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

    /**
     * 校验代收金额是否在范围内
     *
     * @param currency 货币类型
     * @param amount   金额
     * @return true 在范围内，false 不在范围内
     */
    public boolean validatePayinAmount(Currency currency, BigDecimal amount) {
        if (supportCurrencies == null || supportCurrencies.isEmpty()) {
            return true;
        }

        Optional<PayGatewayCurrencyLimit> limitOptional = supportCurrencies.stream()
                .filter(limit -> limit.getCurrency() == currency)
                .findFirst();

        return limitOptional.map(limit -> limit.isPayinAmountValid(amount)).orElse(false);
    }

    /**
     * 校验代付金额是否在范围内
     *
     * @param currency 货币类型
     * @param amount   金额
     * @return true 在范围内，false 不在范围内
     */
    public boolean validatePayoutAmount(Currency currency, BigDecimal amount) {
        if (supportCurrencies == null || supportCurrencies.isEmpty()) {
            return true;
        }

        Optional<PayGatewayCurrencyLimit> limitOptional = supportCurrencies.stream()
                .filter(limit -> limit.getCurrency() == currency)
                .findFirst();

        return limitOptional.map(limit -> limit.isPayoutAmountValid(amount)).orElse(false);
    }

    /**
     * 检查是否支持某个货币
     *
     * @param currency 货币类型
     * @return true 支持，false 不支持
     */
    public boolean supportCurrency(Currency currency) {
        if (supportCurrencies == null || supportCurrencies.isEmpty()) {
            return false;
        }

        return supportCurrencies.stream()
                .anyMatch(limit -> limit.getCurrency() == currency);
    }
}
