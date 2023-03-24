package org.evolboot.system.remote.startuppage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.evolboot.system.domain.startuppage.StartupPage;
import org.evolboot.system.domain.startuppage.StartupPageLocale;
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
public class StartupPageLocaleResponse {


    private Long id;

    private StartupPageLocale locale;

    public static StartupPageLocaleResponse of(StartupPage row) {
        StartupPageLocale locale = row.findLocaleByCurrentLanguage(StartupPageLocale.class);
        return new StartupPageLocaleResponse(row.id(), locale);
    }


    /**
     * 分页
     *
     * @param page
     * @return
     */
    public static Page<StartupPageLocaleResponse> of(Page<StartupPage> page) {
        return PageImpl.<StartupPageLocaleResponse>builder()
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
    public static List<StartupPageLocaleResponse> of(List<StartupPage> list) {
        return list.stream().map(StartupPageLocaleResponse::of).collect(Collectors.toList());
    }

}
