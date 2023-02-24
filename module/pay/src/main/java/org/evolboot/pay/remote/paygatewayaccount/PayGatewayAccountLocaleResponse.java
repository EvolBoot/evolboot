package org.evolboot.pay.remote.paygatewayaccount;

import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.pay.domain.paygatewayaccount.PayGatewayAccount;
import org.evolboot.pay.domain.paygatewayaccount.PayGatewayAccountLocale;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author evol
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PayGatewayAccountLocaleResponse {


    private Long id;
    private String logo;

    private PayGatewayAccountLocale locale;

    private BigDecimal minimumReceipt;

    private BigDecimal maximumReceipt;

    public static PayGatewayAccountLocaleResponse of(PayGatewayAccount row) {
        PayGatewayAccountLocale locale = row.findLocaleByCurrentLanguage(PayGatewayAccountLocale.class);
        return new PayGatewayAccountLocaleResponse(
                row.id(), row.getLogo(), locale, row.getMinimumReceipt(), row.getMaximumReceipt()
        );
    }


    /**
     * 分页
     *
     * @param page
     * @return
     */
    public static Page<PayGatewayAccountLocaleResponse> of(Page<PayGatewayAccount> page) {
        return PageImpl.<PayGatewayAccountLocaleResponse>builder()
                .page(page.getPage())
                .limit(page.getLimit())
                .totalSize(page.getTotalSize())
                .list(of(page.getList()))
                .build();
    }

    /**
     * 列表
     *
     * @param list
     * @return
     */
    public static List<PayGatewayAccountLocaleResponse> of(List<PayGatewayAccount> list) {
        return list.stream().map(PayGatewayAccountLocaleResponse::of).collect(Collectors.toList());
    }

}
