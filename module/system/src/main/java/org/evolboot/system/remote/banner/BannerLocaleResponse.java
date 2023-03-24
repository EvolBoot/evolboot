package org.evolboot.system.remote.banner;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.evolboot.system.domain.banner.Banner;
import org.evolboot.system.domain.banner.BannerLocale;
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
public class BannerLocaleResponse {


    private Long id;

    private BannerLocale locale;

    public static BannerLocaleResponse of(Banner row) {
        BannerLocale locale = row.findLocaleByCurrentLanguage(BannerLocale.class);
        return new BannerLocaleResponse(row.id(), locale);
    }


    /**
     * 分页
     *
     * @param page
     * @return
     */
    public static Page<BannerLocaleResponse> of(Page<Banner> page) {
        return PageImpl.<BannerLocaleResponse>builder()
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
    public static List<BannerLocaleResponse> of(List<Banner> list) {
        return list.stream().map(BannerLocaleResponse::of).collect(Collectors.toList());
    }

}
