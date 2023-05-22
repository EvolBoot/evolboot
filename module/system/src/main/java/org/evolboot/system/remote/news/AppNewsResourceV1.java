package org.evolboot.system.remote.news;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.annotation.ApiClient;
import org.evolboot.core.data.Page;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.system.domain.news.entity.News;
import org.evolboot.system.domain.news.NewsAppService;
import org.evolboot.system.domain.news.service.NewsQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 新闻
 *
 * @author evol
 */
@Slf4j
@RestController
@RequestMapping("/v1/api")
@Tag(name = "新闻", description = "新闻")
@ApiClient
public class AppNewsResourceV1 {

    private final NewsAppService service;

    public AppNewsResourceV1(NewsAppService service) {
        this.service = service;
    }


    /*

      @Operation(summary = "查询新闻")
      @GetMapping("/news")
      public ResponseModel<List<NewsLocaleResponse>> findAll(
      ) {
          List<News> result = service.findAll();
          return ResponseModel.ok(NewsLocaleResponse.of(result));
      }
  */
    @Operation(summary = "查询新闻")
    @GetMapping("/news")
    public ResponseModel<Page<NewsLocaleResponse>> page(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "limit", defaultValue = "20") Integer limit
    ) {
        NewsQuery query = NewsQuery
                .builder()
                .page(page)
                .limit(limit)
                .show(true)
                .build();
        Page<News> result = service.page(query);
        return ResponseModel.ok(NewsLocaleResponse.of(result));
    }


}
