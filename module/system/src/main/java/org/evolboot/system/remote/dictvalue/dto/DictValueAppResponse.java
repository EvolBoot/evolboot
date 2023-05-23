package org.evolboot.system.remote.dictvalue.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.system.domain.dictvalue.entity.DictValue;

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
public class DictValueAppResponse {


    private String displayName;

    private String value;

    public static DictValueAppResponse of(DictValue row) {

        return new DictValueAppResponse(row.getDisplayName(), row.getValue());
    }


    /**
     * 分页
     *
     * @param page
     * @return
     */
    public static Page<DictValueAppResponse> of(Page<DictValue> page) {
        return PageImpl.<DictValueAppResponse>builder()
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
    public static List<DictValueAppResponse> of(List<DictValue> list) {
        return list.stream().map(DictValueAppResponse::of).collect(Collectors.toList());
    }

}
