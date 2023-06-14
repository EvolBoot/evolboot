package org.evolboot.system.remote.news;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.annotation.AdminClient;
import org.evolboot.core.annotation.OperationLog;
import org.evolboot.core.data.Direction;
import org.evolboot.core.data.Page;
import org.evolboot.core.remote.DomainId;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.system.domain.news.entity.News;
import org.evolboot.system.domain.news.NewsAppService;
import org.evolboot.system.domain.news.service.NewsQuery;
import org.evolboot.system.remote.news.dto.NewsCreateRequest;
import org.evolboot.system.remote.news.dto.NewsUpdateRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.evolboot.security.api.access.AccessAuthorities.HAS_ROLE_ADMIN;
import static org.evolboot.security.api.access.AccessAuthorities.or;
import static org.evolboot.system.SystemAccessAuthorities.News.*;

/**
 * 新闻
 *
 * @author evol
 */
@Slf4j
@RestController
@RequestMapping("/v1/admin/system")
@Tag(name = "新闻", description = "新闻")
@AdminClient
public class AdminNewsResourceV1 {

    private final NewsAppService service;

    public AdminNewsResourceV1(NewsAppService service) {
        this.service = service;
    }


    @Operation(summary = "创建新闻")
    @OperationLog("创建新闻")
    @PostMapping("/news")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_CREATE)
    public ResponseModel<?> create(
            @RequestBody @Valid
            NewsCreateRequest request
    ) {
        News news = service.create(request);
        return ResponseModel.ok(new DomainId(news.id()));
    }


    @Operation(summary = "删除新闻")
    @OperationLog("删除新闻")
    @DeleteMapping("/news/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_DELETE)
    public ResponseModel<?> delete(
            @PathVariable("id") Long id
    ) {
        service.delete(id);
        return ResponseModel.ok();
    }


    @Operation(summary = "修改新闻")
    @OperationLog("修改新闻")
    @PutMapping("/news")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_UPDATE)
    public ResponseModel<?> update(
            @RequestBody @Valid
            NewsUpdateRequest request
    ) {
        service.update( request);
        return ResponseModel.ok();
    }

    @Operation(summary = "查询新闻")
    @OperationLog("查询新闻")
    @GetMapping("/news")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_PAGE)
    public ResponseModel<Page<News>> page(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "limit", defaultValue = "20") Integer limit,
            @RequestParam(required = false) String orderField,
            @RequestParam(required = false) Direction order
    ) {
        NewsQuery query = NewsQuery
                .builder()
                .page(page)
                .limit(limit)
                .order(order)
                .orderField(orderField)
                .build();
        Page<News> response = service.page(query);
        return ResponseModel.ok(response);
    }


    @Operation(summary = "查询单个新闻")
    @OperationLog("查询单个新闻")
    @GetMapping("/news/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_SINGLE)
    public ResponseModel<News> get(
            @PathVariable("id") Long id
    ) {
        return ResponseModel.ok(service.findById(id));
    }

}
