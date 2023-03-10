package org.evolboot.content.remote.banner;

import org.evolboot.content.domain.banner.Banner;
import org.evolboot.content.domain.banner.BannerAppService;
import org.evolboot.content.domain.banner.BannerQuery;
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

import static org.evolboot.content.ContentAccessAuthorities.Banner.*;
import static org.evolboot.security.api.access.AccessAuthorities.HAS_ROLE_ADMIN;
import static org.evolboot.security.api.access.AccessAuthorities.or;

/**
 * banner
 *
 * @author evol
 * 
 */
@Slf4j
@RestController
@RequestMapping("/v1/admin/content")
@Tag(name = "banner", description = "banner")
@AdminClient
public class AdminBannerResourceV1 {

    private final BannerAppService service;

    public AdminBannerResourceV1(BannerAppService service) {
        this.service = service;
    }


    @Operation(summary = "创建banner")
    @OperationLog("创建banner")
    @PostMapping("/banner")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_CREATE)
    public ResponseModel<?> create(
            @RequestBody @Valid
                    BannerCreateRequest request
    ) {
        Banner banner = service.create(request);
        return ResponseModel.ok(new DomainId(banner.id()));
    }


    @Operation(summary = "删除banner")
    @OperationLog("删除banner")
    @DeleteMapping("/banner/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_DELETE)
    public ResponseModel<?> delete(
            @PathVariable("id") Long id
    ) {
        service.delete(id);
        return ResponseModel.ok();
    }


    @Operation(summary = "修改banner")
    @OperationLog("修改banner")
    @PutMapping("/banner/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_UPDATE)
    public ResponseModel<?> update(
            @PathVariable("id") Long id,
            @RequestBody @Valid
                    BannerUpdateRequest request
    ) {
        service.update(id, request);
        return ResponseModel.ok();
    }

    @Operation(summary = "查询banner")
    @OperationLog("查询banner")
    @GetMapping("/banner")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_PAGE)
    public ResponseModel<Page<Banner>> page(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "limit", defaultValue = "20") Integer limit
    ) {
        BannerQuery query = BannerQuery
                .builder()
                .page(page)
                .limit(limit)
                .build();
        Page<Banner> response = service.page(query);
        return ResponseModel.ok(response);
    }


    @Operation(summary = "查询单个banner")
    @OperationLog("查询单个banner")
    @GetMapping("/banner/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_SINGLE)
    public ResponseModel<Banner> get(
            @PathVariable("id") Long id
    ) {
        return ResponseModel.ok(service.findById(id));
    }

}
