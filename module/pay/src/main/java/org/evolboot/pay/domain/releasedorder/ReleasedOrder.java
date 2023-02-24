package org.evolboot.pay.domain.releasedorder;

import org.evolboot.core.data.jpa.JpaAbstractEntity;
import org.evolboot.core.domain.AggregateRoot;
import org.evolboot.core.domain.IdGenerate;
import org.evolboot.core.util.ExtendDateUtil;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.shared.pay.PayGateway;
import org.evolboot.shared.pay.ReleasedOrderStatus;
import org.evolboot.shared.pay.ReleasedOrderType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.math.BigDecimal;

import static org.evolboot.shared.pay.ReleasedOrderStatus.PENDING;


/**
 * 代付订单
 *
 * @author evol
* 
 */
@Table(name = "evoltb_pay_released_order")
@Getter
@Slf4j
@NoArgsConstructor
@Entity
public class ReleasedOrder extends JpaAbstractEntity<String> implements AggregateRoot<ReleasedOrder> {

    private final static String ID_PREFIX = "PAYOUT";

    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    private ReleasedOrderType type;

    /**
     * 内部订单号
     */
    private String internalOrderId;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 客户姓名
     */
    private String customName;

    /**
     * 客户电话
     */
    private String customMobile;

    /**
     * 客户邮箱
     */
    private String customEmail;

    /**
     * 持卡人名称
     */
    private String bankBankCardholderName;

    /**
     * 银行编号
     */
    private String bankCode;

    /**
     * 银行卡卡号
     */
    private String bankNo;

    /**
     * 网关账户ID
     */
    private Long payGatewayAccountId;

    /**
     * 网关
     */
    @Enumerated(EnumType.STRING)
    private PayGateway payGateway;

    /**
     * 手续费
     */
    private BigDecimal poundage;

    /**
     * 外部订单号
     */
    private String foreignOrderId;

    /**
     * 请求参数
     */
    @Embedded
    private ReleasedOrderRequestResult requestResult;

    /**
     * 通知参数
     */
    @Embedded
    private ReleasedOrderNotifyResult notifyResult;

    @Enumerated(EnumType.STRING)
    private ReleasedOrderStatus status;

    public ReleasedOrder(String id, ReleasedOrderType type, String internalOrderId, BigDecimal amount, String customName, String customMobile, String customEmail, String bankBankCardholderName, String bankCode, String bankNo, Long payGatewayAccountId, PayGateway payGateway, BigDecimal poundage, String foreignOrderId, ReleasedOrderRequestResult requestResult, ReleasedOrderStatus status) {
        this.id = id;
        this.type = type;
        this.internalOrderId = internalOrderId;
        this.amount = amount;
        this.customName = customName;
        this.customMobile = customMobile;
        this.customEmail = customEmail;
        this.bankBankCardholderName = bankBankCardholderName;
        this.bankCode = bankCode;
        this.bankNo = bankNo;
        this.payGateway = payGateway;
        this.payGatewayAccountId = payGatewayAccountId;
        this.poundage = poundage;
        this.foreignOrderId = foreignOrderId;
        this.requestResult = requestResult;
        this.status = status;
    }

    boolean success(ReleasedOrderNotifyResult result) {
        log.info("下发成功通知:{},{}", id, result);
        if (PENDING.equals(this.status)) {
            this.notifyResult = result;
            this.status = ReleasedOrderStatus.SUCCESS;
            if (ExtendObjects.nonNull(result) && ExtendObjects.nonNull(result.getPoundage())) {
                this.poundage = result.getPoundage();
            }
            return true;
        }
        log.info("下发成功通知:重复通知,已经通过的下发订单:{},{}", id, result);
        return false;
    }

    boolean fail(ReleasedOrderNotifyResult result) {
        log.info("下发失败通知:{},{}", id, result);
        if (PENDING.equals(this.status)) {
            this.notifyResult = result;
            this.status = ReleasedOrderStatus.FAIL;
            return true;
        }
        log.info("下发失败通知:重复通知,已经通过的下发订单:{},{}", id, result);
        return false;
    }

    public static String generateId() {
        return ID_PREFIX + ExtendDateUtil.intOfToDay() + IdGenerate.longId();
    }


    @Override
    public String id() {
        return id;
    }

    @Override
    public ReleasedOrder root() {
        return this;
    }
}
