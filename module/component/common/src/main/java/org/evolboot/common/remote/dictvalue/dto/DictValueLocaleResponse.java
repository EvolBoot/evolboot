package org.evolboot.common.remote.dictvalue.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.common.domain.dictvalue.entity.DictValue;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author evol
 * @date 2023-05-07 12:55:06
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DictValueLocaleResponse {


    private Long id;

    public static DictValueLocaleResponse of(DictValue row) {
//        DictValueLocale locale = row.findLocaleByCurrentLanguage(DictValueLocale.class);

        return new DictValueLocaleResponse();
    }


    /**
     * 分页
     *
     * @param page
     * @return
     */
    public static Page<DictValueLocaleResponse> of(Page<DictValue> page) {
        return PageImpl.<DictValueLocaleResponse>builder()
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
    public static List<DictValueLocaleResponse> of(List<DictValue> list) {
        return list.stream().map(DictValueLocaleResponse::of).collect(Collectors.toList());
    }

}
