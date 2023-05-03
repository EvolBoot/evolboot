package org.evolboot.pay.domain.receiptorder;

import org.evolboot.core.data.jpa.JpaAbstractEntity;
import org.evolboot.core.domain.AggregateRoot;
import org.evolboot.core.domain.IdGenerate;
import org.evolboot.core.util.ExtendDateUtil;
import org.evolboot.shared.pay.Currency;
import org.evolboot.shared.pay.PayGateway;
import org.evolboot.shared.pay.ReceiptOrderStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.math.BigDecimal;


/**
 * 第三方代收订单
 *
 * @author evol
 */
@Table(name = "gh_pay_receipt_order")
@Getter
@Slf4j
@NoArgsConstructor
@Entity
public class ReceiptOrder extends JpaAbstractEntity<String> implements AggregateRoot<ReceiptOrder> {

    private final static String ID_PREFIX = "PAYIN";

    @Id
    private String id;

    /**
     * 代收订单ID
     */
    private String internalOrderId;

    /**
     * 商品信息
     */
    private String productName;

    /**
     * 支付人姓名
     */
    private String payeeName;

    /**
     * 支付人手机号
     */
    private String payeePhone;

    /**
     * 支付人邮箱
     */
    private String payeeEmail;

    /**
     * 回调地址
     */
    private String redirectUrl;


    /**
     * 货币
     */
    private Currency currency;
    /**
     * 请求第三方返回的结果信息
     */
    @Embedded
    private ReceiptOrderRequestResult requestResult;

    /**
     * 第三方通知返回的结果信息
     */
    @Embedded
    private ReceiptOrderNotifyResult notifyResult;

    /**
     * 第三方账户ID
     */
    private Long payGatewayAccountId;

    /**
     * 第三方支付网关
     */
    private PayGateway payGateway;

    /**
     * 支付金额
     */
    private BigDecimal payAmount;

    /**
     * 状态
     */
    private ReceiptOrderStatus status = ReceiptOrderStatus.PENDING;


    private void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    private void setInternalOrderId(String merchantReceiptOrderId) {
        this.internalOrderId = merchantReceiptOrderId;
    }


    private void setProductName(String productName) {
        this.productName = productName;
    }

    private void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    private void setRequestResult(ReceiptOrderRequestResult requestResult) {
        this.requestResult = requestResult;
    }

    private void setNotifyResult(ReceiptOrderNotifyResult notifyResult) {
        this.notifyResult = notifyResult;
    }

    private void setPayGatewayAccountId(Long payAccountId) {
        this.payGatewayAccountId = payAccountId;
    }


    private void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }


    private void setStatus(ReceiptOrderStatus status) {
        this.status = status;

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

    public boolean success(ReceiptOrderNotifyResult notifyResult) {
        if (!ReceiptOrderStatus.PENDING.equals(this.status)) {
            log.error("支付回调:{}, 当前状态:{}  已更新过状态了，重复通知", this.id, this.status);
            return false;
        }
        //TODO 校验支付金额
        log.info("支付回调:更新订单状态为成功:{}", this.id);
        setStatus(ReceiptOrderStatus.SUCCESS);
        setNotifyResult(notifyResult);
        return true;
    }

    public boolean fail(ReceiptOrderNotifyResult notifyResult) {
        if (!ReceiptOrderStatus.PENDING.equals(this.status)) {
            log.error("支付回调:{}, 当前状态:{}  已更新过状态了，重复通知", this.id, this.status);
            return false;
        }
        log.info("支付回调:更新订单状态为失败:{}", this.id);
        setStatus(ReceiptOrderStatus.FAIL);
        setNotifyResult(notifyResult);
        return true;
    }

    public ReceiptOrder(
            String receiptOrderId,
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
            ReceiptOrderRequestResult result

    ) {
        this.id = receiptOrderId;
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
    public ReceiptOrder root() {
        return this;
    }
}
