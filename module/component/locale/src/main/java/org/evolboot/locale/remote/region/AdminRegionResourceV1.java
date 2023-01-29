package org.evolboot.locale.remote.region;

import org.evolboot.core.annotation.AdminClient;
import org.evolboot.core.annotation.OperationLog;
import org.evolboot.core.remote.DomainId;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.locale.domain.region.Region;
import org.evolboot.locale.domain.region.RegionAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.evolboot.locale.LocaleAccessAuthorities.Region.*;
import static org.evolboot.security.api.access.AccessAuthorities.HAS_ROLE_ADMIN;
import static org.evolboot.security.api.access.AccessAuthorities.or;

/**
 * @author evol
 */
@RestController
@RequestMapping("/v1/admin")
@Tag(name = "Region", description = "地区管理")
@AdminClient
public class AdminRegionResourceV1 {

    private final RegionAppService service;

    public AdminRegionResourceV1(RegionAppService service) {
        this.service = service;
    }

    @Operation(summary = "创建地区")
    @OperationLog("创建地区")
    @PostMapping("/regions")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_CREATE)
    public ResponseModel<?> create(
            @RequestBody @Valid
                    RegionCreateRequest request
    ) {
        Long id = service.create(
                request
        );
        return ResponseModel.ok(new DomainId(id));
    }


    @Operation(summary = "删除地区")
    @OperationLog("删除地区")
    @DeleteMapping("/regions/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_DELETE)
    public ResponseModel<?> delete(
            @PathVariable("id") Long id
    ) {
        service.delete(id);
        return ResponseModel.ok();
    }

    @Operation(summary = "修改地区")
    @OperationLog("修改地区")
    @PutMapping("/regions/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_UPDATE)
    public ResponseModel<?> update(
            @PathVariable("id") Long id,
            @RequestBody @Valid
                    RegionUpdateRequest request
    ) {
        service.update(id, request);
        return ResponseModel.ok();
    }

    @Operation(summary = "启用地区")
    @OperationLog("启用地区")
    @PutMapping("/regions/{id}/enable")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_STATUS_ENABLE)
    public ResponseModel<?> enable(
            @PathVariable("id") Long id
    ) {
        service.enable(id);
        return ResponseModel.ok();
    }

    @Operation(summary = "禁用地区")
    @OperationLog("禁用地区")
    @PutMapping("/regions/{id}/disable")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_STATUS_DISABLE)
    public ResponseModel<?> disable(
            @PathVariable("id") Long id
    ) {
        service.disable(id);
        return ResponseModel.ok();
    }

    @Operation(summary = "查询地区")
    @OperationLog("查询地区")
    @GetMapping("/regions")
    public ResponseModel<List<Region>> page(
    ) {
        return ResponseModel.ok(service.findAllByEnableIsTrue());
    }

    @Operation(summary = "查询单个地区")
    @OperationLog("查询单个地区")
    @GetMapping("/regions/{id}")
    public ResponseModel<Region> get(
            @PathVariable("id") Long id
    ) {
        return ResponseModel.ok(service.findById(id));
    }

}
