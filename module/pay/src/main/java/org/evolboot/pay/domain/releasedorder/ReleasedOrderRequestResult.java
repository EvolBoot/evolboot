package org.evolboot.pay.domain.releasedorder;

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
public class ReleasedOrderRequestResult {


    @Column(name = "request_text_")
    private String requestText;

    /**
     *
     */
    @Column(name = "request_result_text_")
    private String resultText;

    /**
     *
     */
    @Column(name = "request_result_foreign_order_id_")
    private String foreignOrderId;


    /**
     * 请求时返回的代收状态
     */
    @Column(name = "request_result_foreign_status_")
    private String foreignStatus;

}
