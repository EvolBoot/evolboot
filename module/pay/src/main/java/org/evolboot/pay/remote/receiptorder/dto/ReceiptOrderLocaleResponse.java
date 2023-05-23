package org.evolboot.pay.remote.receiptorder.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.pay.domain.receiptorder.entity.ReceiptOrder;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author evol
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptOrderLocaleResponse {


    private Long id;

    public static ReceiptOrderLocaleResponse of(ReceiptOrder row) {
//        ReceiptOrderLocale locale = row.findLocaleByCurrentLanguage(ReceiptOrderLocale.class);

        return new ReceiptOrderLocaleResponse();
    }


    /**
     * 分页
     *
     * @param page
     * @return
     */
    public static Page<ReceiptOrderLocaleResponse> of(Page<ReceiptOrder> page) {
        return PageImpl.<ReceiptOrderLocaleResponse>builder()
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
    public static List<ReceiptOrderLocaleResponse> of(List<ReceiptOrder> list) {
        return list.stream().map(ReceiptOrderLocaleResponse::of).collect(Collectors.toList());
    }

}
