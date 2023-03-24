package org.evolboot.system.remote.qa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.evolboot.system.domain.qa.Qa;
import org.evolboot.system.domain.qa.QaLocale;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author evol
 * 
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QaLocaleResponse {


    private Long id;

    private QaLocale locale;

    public static QaLocaleResponse of(Qa row) {
        QaLocale locale = row.findLocaleByCurrentLanguage(QaLocale.class);
        return new QaLocaleResponse(row.id(), locale);
    }


    /**
     * 分页
     *
     * @param page
     * @return
     */
    public static Page<QaLocaleResponse> of(Page<Qa> page) {
        return PageImpl.<QaLocaleResponse>builder()
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
    public static List<QaLocaleResponse> of(List<Qa> list) {
        return list.stream().map(QaLocaleResponse::of).collect(Collectors.toList());
    }

}
