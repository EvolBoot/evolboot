package org.evolboot.pay.domain.releasedorder.entity;

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
public class ReleasedOrderQueryResult {


    @Column(name = "query_request_text_")
    private String requestText;

    /**
     *
     */
    @Column(name = "query_result_text_")
    private String resultText;

    /**
     *
     */
    @Column(name = "query_result_foreign_order_id_")
    private String foreignOrderId;


    /**
     * 请求时返回的代收状态
     */
    @Column(name = "query_result_foreign_state_")
    private String foreignState;

}
