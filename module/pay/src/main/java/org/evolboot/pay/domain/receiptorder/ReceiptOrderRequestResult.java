package org.evolboot.pay.domain.receiptorder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author evol
 */
@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptOrderRequestResult {

    @Column(name = "request_result_foreign_order_id_")
    private String foreignOrderId;

    @Column(name = "request_result_pay_url_")
    private String payUrl;

    @Column(name = "request_result_request_text_")
    private String requestText;


}
