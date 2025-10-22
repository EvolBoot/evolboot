package org.evolboot.pay.domain.payinorder.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.data.jpa.JpaAbstractEntity;
import org.evolboot.core.entity.AggregateRoot;
import org.evolboot.core.entity.IdGenerate;
import org.evolboot.core.util.ExtendDateUtil;
import org.evolboot.shared.pay.Currency;
import org.evolboot.shared.pay.PayGateway;
import org.evolboot.shared.pay.PayinOrderState;

import java.math.BigDecimal;


/**
 * 第三方代收订单
 *
 * @author evol
 */
@Table(name = "evoltb_pay_payin_order")
@Getter
@Slf4j
@NoArgsConstructor
@Entity
@Schema(title = "第三方代收订单")
public class PayinOrder extends JpaAbstractEntity<String> implements AggregateRoot<PayinOrder> {

    private final static String ID_PREFIX = "PAYIN";

    @Id
    private String id;

    /**
     * 代收订单ID
     */
    @Schema(title = "代收订单ID")
    private String internalOrderId;

    /**
     * 商品信息
     */
    @Schema(title = "商品信息")
    private String productName;

    /**
     * 支付人姓名
     */
    @Schema(title = "支付人姓名")
    private String payeeName;

    /**
     * 支付人手机号
     */
    @Schema(title = "支付人手机号")
    private String payeePhone;

    /**
     * 支付人邮箱
     */
    @Schema(title = "支付人邮箱")
    private String payeeEmail;

    /**
     * 回调地址
     */
    @Schema(title = "回调地址")
    private String redirectUrl;


    /**
     * 货币
     */
    @Schema(title = "货币")
    private Currency currency;
    /**
     * 请求第三方返回的结果信息
     */
    @Schema(title = "请求第三方返回的结果信息")
    @Embedded
    private PayinOrderRequestResult requestResult;

    /**
     * 第三方通知返回的结果信息
     */
    @Schema(title = "第三方通知返回的结果信息")
    @Embedded
    private PayinOrderNotifyResult notifyResult;

    /**
     * 第三方账户ID
     */
    @Schema(title = "第三方账户ID")
    private Long payGatewayAccountId;

    /**
     * 第三方支付网关
     */
    @Schema(title = "第三方支付网关")
    @Enumerated(EnumType.STRING)
    private PayGateway payGateway;

    /**
     * 支付金额
     */
    @Schema(title = "支付金额")
    private BigDecimal payAmount;

    /**
     * 状态
     */
    @Schema(title = "状态")
    @Enumerated(EnumType.STRING)
    private PayinOrderState state = PayinOrderState.PENDING;


    private void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    private void setInternalOrderId(String merchantPayinOrderId) {
        this.internalOrderId = merchantPayinOrderId;
    }


    private void setProductName(String productName) {
        this.productName = productName;
    }

    private void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    private void setRequestResult(PayinOrderRequestResult requestResult) {
        this.requestResult = requestResult;
    }

    private void setNotifyResult(PayinOrderNotifyResult notifyResult) {
        this.notifyResult = notifyResult;
    }

    private void setPayGatewayAccountId(Long payAccountId) {
        this.payGatewayAccountId = payAccountId;
    }


    private void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }


    private void setState(PayinOrderState state) {
        this.state = state;

    }

    private void setPayGateway(PayGateway gateway) {
        this.payGateway = gateway;
    }

    public void setPayeePhone(String payeePhone) {
        this.payeePhone = payeePhone;
    }

    public void setPayeeEmail(String payeeEmail) {
        this.payeeEmail = payeeEmail;
    }

    public boolean success(PayinOrderNotifyResult notifyResult) {
        if (!PayinOrderState.PENDING.equals(this.state)) {
            log.error("支付回调:{}, 当前状态:{}  已更新过状态了，重复通知", this.id, this.state);
            return false;
        }
        //TODO 校验支付金额
        log.info("支付回调:更新订单状态为成功:{}", this.id);
        setState(PayinOrderState.SUCCESS);
        setNotifyResult(notifyResult);
        return true;
    }

    public boolean fail(PayinOrderNotifyResult notifyResult) {
        if (!PayinOrderState.PENDING.equals(this.state)) {
            log.error("支付回调:{}, 当前状态:{}  已更新过状态了，重复通知", this.id, this.state);
            return false;
        }
        log.info("支付回调:更新订单状态为失败:{}", this.id);
        setState(PayinOrderState.FAIL);
        setNotifyResult(notifyResult);
        return true;
    }

    public PayinOrder(
            String payinOrderId,
            String internalOrderId,
            String productName,
            String payeeName,
            String payeePhone,
            String payeeEmail,
            Long payGatewayAccountId,
            BigDecimal payAmount,
            PayGateway payGateway,
            String redirectUrl,
            Currency currency,
            PayinOrderRequestResult result

    ) {
        this.id = payinOrderId;
        setInternalOrderId(internalOrderId);
        setProductName(productName);
        setPayeeName(payeeName);
        setPayeePhone(payeePhone);
        setPayeeEmail(payeeEmail);
        setPayGatewayAccountId(payGatewayAccountId);
        setPayAmount(payAmount);
        setPayGateway(payGateway);
        setRedirectUrl(redirectUrl);
        setRequestResult(result);
        this.currency = currency;
    }


    public static String generateId() {
        return ID_PREFIX + ExtendDateUtil.intOfToDay() + IdGenerate.longId();
    }


    @Override
    public String id() {
        return id;
    }

    @Override
    public PayinOrder root() {
        return this;
    }
}
