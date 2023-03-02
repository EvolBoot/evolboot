package org.evolboot.pay.domain.releasedorder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.math.BigDecimal;

/**
 * @author evol
 */
@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReleasedOrderNotifyResult {


    /**
     * 第三方平台的交易ID
     */
    @Column(name = "notify_result_foreign_order_id_")
    private String foreignOrderId;

    /**
     * 内部订单ID
     */
    @Column(name = "notify_result_released_order_id_")
    private String releasedOrderId;

    /**
     * 第三方 支付状态
     */
    @Column(name = "notify_result_foreign_status_")
    private String foreignStatus;

    /**
     * 通知的整个Json
     */
    @Column(name = "notify_result_text_")
    private String notifyText;

    /**
     * 支付金额
     */
    @Column(name = "notify_result_amount_")
    private BigDecimal amount;

    /**
     * 通知手续费
     */
    @Column(name = "notify_result_poundage_")
    private BigDecimal poundage;

}
