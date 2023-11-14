package org.evolboot.pay.domain.receiptorder.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 * @author evol
 */
@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptOrderRequestResult {

    /**
     * 请求返回的第三方ID
     */
    @Column(name = "request_result_foreign_order_id_")
    private String foreignOrderId;

    /**
     * 请求返回的支付URL
     */
    @Column(name = "request_result_pay_url_")
    private String payUrl;

    /**
     * 请求时返回的代收状态
     */
    @Column(name = "request_result_foreign_state_")
    private String foreignState;

    /**
     * 请求返回的全部文本信息
     */
    @Column(name = "request_result_request_text_")
    private String requestText;


}
