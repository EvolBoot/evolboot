package org.evolboot.pay.domain.receiptorder.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.math.BigDecimal;

/**
 * @author evol
 */
@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptOrderNotifyResult {

    // 第三方平台的交易ID
    @Column(name = "notify_result_foreign_order_id_")
    private String foreignOrderId;

    // 我们这边的ID
    @Column(name = "notify_result_receipt_order_id_")
    private String receiptOrderId;

    // 支付状态
    @Column(name = "notify_result_foreign_state_")
    private String foreignState;

    // 通知的整个Json
    @Column(name = "notify_result_notify_text_")
    private String notifyText;


    // 支付金额
    @Column(name = "notify_result_pay_amount_")
    private BigDecimal payAmount;

    // 扣除手续费后金额
    @Column(name = "notify_result_arrival_amount_")
    private BigDecimal arrivalAmount;

    // 手续费
    @Column(name = "notify_result_poundage_")
    private BigDecimal poundage;
}
