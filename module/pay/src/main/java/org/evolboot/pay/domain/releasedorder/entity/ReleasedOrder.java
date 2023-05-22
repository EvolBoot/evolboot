package org.evolboot.pay.domain.releasedorder.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.data.jpa.JpaAbstractEntity;
import org.evolboot.core.domain.AggregateRoot;
import org.evolboot.core.domain.IdGenerate;
import org.evolboot.core.util.ExtendDateUtil;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.shared.pay.Currency;
import org.evolboot.shared.pay.PayGateway;
import org.evolboot.shared.pay.ReleasedOrderOrgType;
import org.evolboot.shared.pay.ReleasedOrderStatus;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

import static org.evolboot.shared.pay.ReleasedOrderStatus.PENDING;
import static org.evolboot.shared.pay.ReleasedOrderStatus.WAIT;


/**
 * 代付订单
 *
 * @author evol
 */
@Table(name = "gh_pay_released_order")
@Getter
@Slf4j
@NoArgsConstructor
@Entity
public class ReleasedOrder extends JpaAbstractEntity<String> implements AggregateRoot<ReleasedOrder> {

    private final static String ID_PREFIX = "PAYOUT";

    @Id
    private String id;

    /**
     * 内部订单号
     */
    private String internalOrderId;

    /**
     * 货币
     */
    private Currency currency;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 客户姓名
     */
    private String payeeName;

    /**
     * 客户电话
     */
    private String payeePhone;

    /**
     * 客户邮箱
     */
    private String payeeEmail;


    /**
     * 银行编号
     */
    private String bankCode;

    /**
     * 银行卡卡号
     */
    private String bankNo;


    /**
     * ifsc 代码
     */
    private String ifscCode;

    /**
     * IFSC 卡号
     */
    private String ifscCardNo;


    private ReleasedOrderOrgType orgType;
    /**
     * 网关账户ID
     */
    private Long payGatewayAccountId;

    /**
     * 网关
     */
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
    private ReleasedOrderCreateResult createResult;

    /**
     * 通知参数
     */
    @Embedded
    private ReleasedOrderNotifyResult notifyResult;

    /**
     * 查询参数
     */
    @Embedded
    private ReleasedOrderQueryResult queryResult;

    /**
     * 状态
     */
    private ReleasedOrderStatus status = WAIT;

    public ReleasedOrder(
            String internalOrderId,
            Currency currency,
            BigDecimal amount,
            String payeeName,
            String payeePhone,
            String payeeEmail,
            String bankCode,
            String bankNo,
            String ifscCode,
            String ifscCardNo,
            ReleasedOrderOrgType orgType,
            Long payGatewayAccountId,
            PayGateway payGateway) {
        this.id = generateId();
        this.internalOrderId = internalOrderId;
        this.currency = currency;
        this.amount = amount;
        this.payeeName = payeeName;
        this.payeePhone = payeePhone;
        this.payeeEmail = payeeEmail;
        this.bankCode = bankCode;
        this.bankNo = bankNo;
        this.ifscCode = ifscCode;
        this.ifscCardNo = ifscCardNo;
        this.orgType = orgType;
        this.payGateway = payGateway;
        this.payGatewayAccountId = payGatewayAccountId;
    }

    /**
     * 处理
     *
     * @param poundage
     * @param foreignOrderId
     * @return
     */
    public boolean pending(BigDecimal poundage,
                           String foreignOrderId) {
        if (!WAIT.equals(this.status)) {
            log.info("代付:{},当前订单不在WAIT状态,不需要重复PENDING", id);
            return false;
        }
        this.poundage = poundage;
        this.foreignOrderId = foreignOrderId;
        return true;
    }

    public boolean success() {
        log.info("代付:成功通知:{}", id);
        if (PENDING.equals(this.status)) {
            this.status = ReleasedOrderStatus.SUCCESS;
            return true;
        }
        log.info("代付成功通知:重复通知,已经通过的代付订单:{}", id);
        return false;
    }


    public boolean fail() {
        log.info("代付:失败通知:{}", id);
        if (PENDING.equals(this.status) || WAIT.equals(this.status)) {

            this.status = ReleasedOrderStatus.FAIL;
            return true;
        }
        log.info("代付:失败通知:重复通知,已经通过的代付订单:{}", id);
        return false;
    }


    public void setNotifyResult(ReleasedOrderNotifyResult result) {
        if (ExtendObjects.nonNull(result) && ExtendObjects.nonNull(result.getPoundage())) {
            this.poundage = result.getPoundage();
        }
        this.notifyResult = result;
    }

    public void setCreateResult(ReleasedOrderCreateResult createResult) {
        this.createResult = createResult;
    }


    public void setQueryResult(ReleasedOrderQueryResult queryResult) {
        this.queryResult = queryResult;
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
