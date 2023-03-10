package org.evolboot.content.remote.notice;

import org.evolboot.content.domain.notice.Notice;
import org.evolboot.content.domain.notice.NoticeAppService;
import org.evolboot.content.domain.notice.NoticeQuery;
import org.evolboot.core.DefaultConfig;
import org.evolboot.core.annotation.ApiClient;
import org.evolboot.core.data.Page;
import org.evolboot.core.i18n.DelegatingLocaleContextHolder;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.core.util.ExtendObjects;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;

/**
 * 公告
 *
 * @author evol
 * 
 */
@Slf4j
@RestController
@RequestMapping("/v1/api/content")
@Tag(name = "公告", description = "公告")
@ApiClient
public class AppNoticeResourceV1 {

    private final NoticeAppService service;

    public AppNoticeResourceV1(NoticeAppService service) {
        this.service = service;
    }


    @Operation(summary = "最新公告")
    @GetMapping("/notice/latest")
    public ResponseModel<NoticeLocaleResponse> findAll(
    ) {
        Notice latest = service.findByLatest();
        return ResponseModel.ok(NoticeLocaleResponse.of(latest));
    }

    @Operation(summary = "最新公告(HTML)")
    @GetMapping("/notice/latest.html")
    public ModelAndView getModelAndView(
            String lang
    ) {
        lang = ExtendObjects.requireNonNullElse(lang, DefaultConfig.getDefaultLanguageTag());
        DelegatingLocaleContextHolder.setLocalLanguage(Locale.forLanguageTag(lang));
        Notice latest = service.findByLatest();
        NoticeLocaleResponse localeResponse = NoticeLocaleResponse.of(latest);
        return new ModelAndView("/notice", "notice", localeResponse);
    }

    /*
        @Operation(summary = "查询公告")
        @GetMapping("/notice")
        public ResponseModel<List<NoticeLocaleResponse>> findAll(
        ) {
            List<Notice> result = service.findAll();
            return ResponseModel.ok(NoticeLocaleResponse.of(result));
        }
        */
    @Operation(summary = "查询公告")
    @GetMapping("/notice")
    public ResponseModel<Page<NoticeLocaleResponse>> page(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "limit", defaultValue = "20") Integer limit
    ) {
        NoticeQuery query = NoticeQuery
                .builder()
                .page(page)
                .limit(limit)
                .enable(true)
                .build();
        Page<Notice> result = service.page(query);
        return ResponseModel.ok(NoticeLocaleResponse.of(result));
    }


}
