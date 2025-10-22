package org.evolboot.pay.remote.payinorder.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.pay.domain.payinorder.entity.PayinOrder;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author evol
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PayinOrderLocaleResponse {


    private Long id;

    public static PayinOrderLocaleResponse of(PayinOrder row) {
        return new PayinOrderLocaleResponse();
    }


    /**
     * 分页
     *
     * @param page
     * @return
     */
    public static Page<PayinOrderLocaleResponse> of(Page<PayinOrder> page) {
        return PageImpl.<PayinOrderLocaleResponse>builder()
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
    public static List<PayinOrderLocaleResponse> of(List<PayinOrder> list) {
        return list.stream().map(PayinOrderLocaleResponse::of).collect(Collectors.toList());
    }

}
