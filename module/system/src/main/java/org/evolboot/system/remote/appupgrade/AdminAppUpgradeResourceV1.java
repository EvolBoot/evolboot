package org.evolboot.system.remote.appupgrade;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.annotation.AdminClient;
import org.evolboot.core.annotation.OperationLog;
import org.evolboot.core.data.Page;
import org.evolboot.core.remote.DomainId;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.system.domain.appupgrade.entity.AppUpgrade;
import org.evolboot.system.domain.appupgrade.AppUpgradeAppService;
import org.evolboot.system.domain.appupgrade.service.AppUpgradeQuery;
import org.evolboot.system.remote.appupgrade.dto.AppUpgradeCreateRequest;
import org.evolboot.system.remote.appupgrade.dto.AppUpgradeUpdateRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.evolboot.security.api.access.AccessAuthorities.HAS_ROLE_ADMIN;
import static org.evolboot.security.api.access.AccessAuthorities.or;
import static org.evolboot.system.SystemAccessAuthorities.AppUpgrade.*;

/**
 * APP更新
 *
 * @author evol
 */
@Slf4j
@RestController
@RequestMapping("/v1/admin/app-upgrade")
@Tag(name = "APP更新", description = "APP更新")
@AdminClient
public class AdminAppUpgradeResourceV1 {

    private final AppUpgradeAppService service;

    public AdminAppUpgradeResourceV1(AppUpgradeAppService service) {
        this.service = service;
    }


    @Operation(summary = "创建APP更新")
    @OperationLog("创建APP更新")
    @PostMapping("")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_CREATE)
    public ResponseModel<?> create(
            @RequestBody @Valid
            AppUpgradeCreateRequest request
    ) {
        AppUpgrade appUpgrade = service.create(request);
        return ResponseModel.ok(new DomainId(appUpgrade.id()));
    }


    @Operation(summary = "删除APP更新")
    @OperationLog("删除APP更新")
    @DeleteMapping("/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_DELETE)
    public ResponseModel<?> delete(
            @PathVariable("id") Long id
    ) {
        service.delete(id);
        return ResponseModel.ok();
    }


    @Operation(summary = "修改APP更新")
    @OperationLog("修改APP更新")
    @PutMapping("/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_UPDATE)
    public ResponseModel<?> update(
            @PathVariable("id") Long id,
            @RequestBody @Valid
            AppUpgradeUpdateRequest request
    ) {
        service.update(id, request);
        return ResponseModel.ok();
    }

    @Operation(summary = "查询APP更新")
    @OperationLog("查询APP更新")
    @GetMapping("")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_PAGE)
    public ResponseModel<Page<AppUpgrade>> page(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "limit", defaultValue = "20") Integer limit
    ) {
        AppUpgradeQuery query = AppUpgradeQuery
                .builder()
                .page(page)
                .limit(limit)
                .build();
        Page<AppUpgrade> response = service.page(query);
        return ResponseModel.ok(response);
    }


    @Operation(summary = "查询单个APP更新")
    @OperationLog("查询单个APP更新")
    @GetMapping("/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_SINGLE)
    public ResponseModel<AppUpgrade> get(
            @PathVariable("id") Long id
    ) {
        return ResponseModel.ok(service.findById(id));
    }

}
