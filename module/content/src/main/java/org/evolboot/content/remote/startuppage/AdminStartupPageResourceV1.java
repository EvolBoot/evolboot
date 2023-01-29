package org.evolboot.content.remote.startuppage;

import org.evolboot.content.domain.startuppage.StartupPage;
import org.evolboot.content.domain.startuppage.StartupPageAppService;
import org.evolboot.content.domain.startuppage.StartupPageQuery;
import org.evolboot.core.annotation.AdminClient;
import org.evolboot.core.annotation.OperationLog;
import org.evolboot.core.data.Page;
import org.evolboot.core.remote.DomainId;
import org.evolboot.core.remote.ResponseModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.evolboot.content.ContentAccessAuthorities.StartupPage.*;
import static org.evolboot.security.api.access.AccessAuthorities.HAS_ROLE_ADMIN;
import static org.evolboot.security.api.access.AccessAuthorities.or;

/**
 * 启动页
 *
 * @author evol
 * 
 */
@Slf4j
@RestController
@RequestMapping("/v1/admin/content")
@Tag(name = "启动页", description = "启动页")
@AdminClient
public class AdminStartupPageResourceV1 {

    private final StartupPageAppService service;

    public AdminStartupPageResourceV1(StartupPageAppService service) {
        this.service = service;
    }


    @Operation(summary = "创建启动页")
    @OperationLog("创建启动页")
    @PostMapping("/startup-page")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_CREATE)
    public ResponseModel<?> create(
            @RequestBody @Valid
                    StartupPageCreateRequest request
    ) {
        StartupPage startupPage = service.create(request);
        return ResponseModel.ok(new DomainId(startupPage.id()));
    }


    @Operation(summary = "删除启动页")
    @OperationLog("删除启动页")
    @DeleteMapping("/startup-page/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_DELETE)
    public ResponseModel<?> delete(
            @PathVariable("id") Long id
    ) {
        service.delete(id);
        return ResponseModel.ok();
    }


    @Operation(summary = "修改启动页")
    @OperationLog("修改启动页")
    @PutMapping("/startup-page/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_UPDATE)
    public ResponseModel<?> update(
            @PathVariable("id") Long id,
            @RequestBody @Valid
                    StartupPageUpdateRequest request
    ) {
        service.update(id, request);
        return ResponseModel.ok();
    }

    @Operation(summary = "查询启动页")
    @OperationLog("查询启动页")
    @GetMapping("/startup-page")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_PAGE)
    public ResponseModel<Page<StartupPage>> page(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "limit", defaultValue = "20") Integer limit
    ) {
        StartupPageQuery query = StartupPageQuery
                .builder()
                .page(page)
                .limit(limit)
                .build();
        Page<StartupPage> response = service.page(query);
        return ResponseModel.ok(response);
    }


    @Operation(summary = "查询单个启动页")
    @OperationLog("查询单个启动页")
    @GetMapping("/startup-page/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_SINGLE)
    public ResponseModel<StartupPage> get(
            @PathVariable("id") Long id
    ) {
        return ResponseModel.ok(service.findById(id));
    }

}
