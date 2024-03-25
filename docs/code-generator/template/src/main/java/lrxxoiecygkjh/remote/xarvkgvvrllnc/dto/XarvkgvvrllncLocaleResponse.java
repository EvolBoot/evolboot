package projectPackage.lrxxoiecygkjh.remote.xarvkgvvrllnc.dto;

import projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.entity.Xarvkgvvrllnc;
import projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.entity.XarvkgvvrllncLocale;

import projectPackage.core.data.Page;
import projectPackage.core.data.PageImpl;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.List;
import java.util.stream.Collectors;

/**
 * @author authorxRQXP
 * @date datePlaceholder
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class XarvkgvvrllncLocaleResponse {


    private Keya2Akk5iV3n id;

    public static XarvkgvvrllncLocaleResponse of(Xarvkgvvrllnc row) {
//        XarvkgvvrllncLocale locale = row.findLocaleByCurrentLanguage(XarvkgvvrllncLocale.class);

        return new XarvkgvvrllncLocaleResponse();
    }


    /**
     * 分页
     *
     * @param page
     * @return
     */
    public static Page<XarvkgvvrllncLocaleResponse> of(Page<Xarvkgvvrllnc> page) {
        return PageImpl.<XarvkgvvrllncLocaleResponse>builder()
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
    public static List<XarvkgvvrllncLocaleResponse> of(List<Xarvkgvvrllnc> list) {
        return list.stream().map(XarvkgvvrllncLocaleResponse::of).collect(Collectors.toList());
    }

}
