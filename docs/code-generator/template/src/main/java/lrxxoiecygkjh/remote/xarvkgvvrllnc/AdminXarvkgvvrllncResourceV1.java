package projectPackage.lrxxoiecygkjh.remote.xarvkgvvrllnc;

import projectPackage.core.annotation.AdminClient;
import projectPackage.core.annotation.OperationLog;
import projectPackage.core.remote.DomainId;
import projectPackage.core.remote.ResponseModel;
import projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.XarvkgvvrllncAppService;
import projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.XarvkgvvrllncQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.entity.Xarvkgvvrllnc;
import projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.dto.XarvkgvvrllncQueryRequest;
import projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.dto.XarvkgvvrllncBatchQueryRequest;
import projectPackage.lrxxoiecygkjh.remote.xarvkgvvrllnc.dto.*;
import projectPackage.core.data.Page;
import projectPackage.shared.lang.CurrentPrincipal;
import projectPackage.security.api.SecurityAccessTokenHolder;

import jakarta.validation.Valid;
import java.util.List;

import static projectPackage.security.api.access.AccessAuthorities.*;
import static projectPackage.lrxxoiecygkjh.LrxxoiecygkjhAccessAuthorities.Xarvkgvvrllnc.*;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * 模板
 *
 * @author authorxRQXP
 * @date datePlaceholder
 */
@Slf4j
@RestController
@RequestMapping("/v1/admin/lh6wf4pd9y/irkdf3nfva")
@Tag(name = "模板", description = "模板")
@AdminClient
public class AdminXarvkgvvrllncResourceV1 {

    private final XarvkgvvrllncAppService appService;
    private final XarvkgvvrllncQueryService queryService;

    public AdminXarvkgvvrllncResourceV1(XarvkgvvrllncAppService appService, XarvkgvvrllncQueryService queryService) {
        this.appService = appService;
        this.queryService = queryService;
    }


    @Operation(summary = "创建模板")
    @OperationLog("创建模板")
    @PostMapping("")
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN + OR + HAS_CREATE)
    public ResponseModel<?> create(
            @RequestBody @Valid
            XarvkgvvrllncCreateRequest request
    ) {
        CurrentPrincipal currentPrincipal = new CurrentPrincipal(SecurityAccessTokenHolder.getUserId(), SecurityAccessTokenHolder.getTenantId());
        Xarvkgvvrllnc instantiationObjectName = appService.create(currentPrincipal, request);
        return ResponseModel.ok(new DomainId(instantiationObjectName.id()));
    }


    @Operation(summary = "删除模板")
    @OperationLog("删除模板")
    @DeleteMapping("/{id}")
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN + OR + HAS_DELETE)
    public ResponseModel<?> delete(
            @PathVariable("id") Keya2Akk5iV3n id
    ) {
        CurrentPrincipal currentPrincipal = new CurrentPrincipal(SecurityAccessTokenHolder.getUserId(), SecurityAccessTokenHolder.getTenantId());
        appService.delete(currentPrincipal, id);
        return ResponseModel.ok();
    }


    @Operation(summary = "修改模板")
    @OperationLog("修改模板")
    @PutMapping
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN + OR + HAS_UPDATE)
    public ResponseModel<?> update(
            @RequestBody @Valid
            XarvkgvvrllncUpdateRequest request
    ) {
        CurrentPrincipal currentPrincipal = new CurrentPrincipal(SecurityAccessTokenHolder.getUserId(), SecurityAccessTokenHolder.getTenantId());
        appService.update(currentPrincipal, request);
        return ResponseModel.ok();
    }

    @Operation(summary = "查询模板")
    @GetMapping("")
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN + OR + HAS_PAGE)
    public ResponseModel<Page<Xarvkgvvrllnc>> page(XarvkgvvrllncBatchQueryRequest request) {
        XarvkgvvrllncQueryRequest query = request.convert(SecurityAccessTokenHolder.getUserId(), SecurityAccessTokenHolder.getTenantId());
        Page<Xarvkgvvrllnc> response = queryService.page(query);
        return ResponseModel.ok(response);
    }


    @Operation(summary = "查询模板(全部)")
    @GetMapping("/all")
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN + OR + HAS_PAGE)
    public ResponseModel<List<Xarvkgvvrllnc>> findAll(XarvkgvvrllncBatchQueryRequest request) {
        XarvkgvvrllncQueryRequest query = request.convert(SecurityAccessTokenHolder.getUserId(), SecurityAccessTokenHolder.getTenantId());
        return ResponseModel.ok(queryService.findAll(query));
    }


    @Operation(summary = "查询单个模板")
    @GetMapping("/{id}")
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN + OR + HAS_SINGLE)
    public ResponseModel<Xarvkgvvrllnc> get(
            @PathVariable("id") Keya2Akk5iV3n id
    ) {
        return ResponseModel.ok(queryService.findById(id));
    }


    @Operation(summary = "批量删除模板")
    @OperationLog("批量删除模板")
    @DeleteMapping("/batch")
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN + OR + HAS_SINGLE)
    public ResponseModel<?> delete(
            @RequestBody XarvkgvvrllncBatchQueryRequest request
    ) {
        appService.delete(request.convert(SecurityAccessTokenHolder.getUserId(), SecurityAccessTokenHolder.getTenantId()));
        return ResponseModel.ok();
    }
}
