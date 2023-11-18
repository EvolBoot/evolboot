package org.evolboot.common.remote.dictkey.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.common.domain.dictkey.entity.DictKey;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author evol
 * @date 2023-05-07 12:29:33
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DictKeyLocaleResponse {


    private Long id;

    public static DictKeyLocaleResponse of(DictKey row) {
//        DictKeyLocale locale = row.findLocaleByCurrentLanguage(DictKeyLocale.class);

        return new DictKeyLocaleResponse();
    }


    /**
     * 分页
     *
     * @param page
     * @return
     */
    public static Page<DictKeyLocaleResponse> of(Page<DictKey> page) {
        return PageImpl.<DictKeyLocaleResponse>builder()
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
    public static List<DictKeyLocaleResponse> of(List<DictKey> list) {
        return list.stream().map(DictKeyLocaleResponse::of).collect(Collectors.toList());
    }

}
