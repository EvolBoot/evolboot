package org.evolboot.common.remote.dictkey;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.annotation.TenantClient;
import org.evolboot.core.annotation.OperationLog;
import org.evolboot.core.data.Direction;
import org.evolboot.core.data.Page;
import org.evolboot.core.remote.DomainId;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.common.domain.dictkey.DictKeyAppService;
import org.evolboot.common.domain.dictkey.entity.DictKey;
import org.evolboot.common.domain.dictkey.dto.DictKeyQueryRequest;
import org.evolboot.common.remote.dictkey.dto.DictKeyCreateRequest;
import org.evolboot.common.remote.dictkey.dto.DictKeyUpdateRequest;
import org.evolboot.security.api.SecurityAccessTokenHolder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Set;

import static org.evolboot.security.api.access.AccessAuthorities.HAS_ROLE_TENANT_OWNER;
import static org.evolboot.security.api.access.AccessAuthorities.OR;
import static org.evolboot.common.CommonAccessAuthorities.DictKey.*;

/**
 * Tenant Dict Key Resource
 * 租户字典Key管理 - 租户所有者和租户员工可访问
 * 管理租户自定义字典
 */
@Slf4j
@RestController
@RequestMapping("/tenant/v1/common/dict-key")
@Tag(name = "租户字典key", description = "租户字典key")
@TenantClient
public class TenantDictKeyResourceV1 {

    private final DictKeyAppService service;

    public TenantDictKeyResourceV1(DictKeyAppService service) {
        this.service = service;
    }

    /**
     * 创建字典key
     */
    @Operation(summary = "创建字典key")
    @OperationLog("创建字典key")
    @PostMapping("")
    @PreAuthorize(HAS_ROLE_TENANT_OWNER + OR + TENANT_HAS_CREATE)
    public ResponseModel<?> create(
            @RequestBody @Valid DictKeyCreateRequest request
    ) {
        DictKey dictKey = service.create(request);
        return ResponseModel.ok(new DomainId(dictKey.id()));
    }

    /**
     * 删除字典key
     */
    @Operation(summary = "删除字典key")
    @OperationLog("删除字典key")
    @DeleteMapping("/{id}")
    @PreAuthorize(HAS_ROLE_TENANT_OWNER + OR + TENANT_HAS_DELETE)
    public ResponseModel<?> delete(
            @PathVariable("id") Long id
    ) {
        service.delete(id);
        return ResponseModel.ok();
    }

    /**
     * 批量删除字典key
     */
    @Operation(summary = "批量删除字典key")
    @OperationLog("批量删除字典key")
    @DeleteMapping()
    @PreAuthorize(HAS_ROLE_TENANT_OWNER + OR + TENANT_HAS_DELETE)
    public ResponseModel<?> delete(
            @RequestBody Set<Long> ids
    ) {
        service.delete(ids);
        return ResponseModel.ok();
    }

    /**
     * 修改字典key
     */
    @Operation(summary = "修改字典key")
    @OperationLog("修改字典key")
    @PutMapping
    @PreAuthorize(HAS_ROLE_TENANT_OWNER + OR + TENANT_HAS_UPDATE)
    public ResponseModel<?> update(
            @RequestBody @Valid DictKeyUpdateRequest request
    ) {
        service.update(request);
        return ResponseModel.ok();
    }

    /**
     * 查询字典key
     * 自动过滤本租户的字典
     */
    @Operation(summary = "查询字典key")
    @GetMapping("")
    @PreAuthorize(HAS_ROLE_TENANT_OWNER + OR + TENANT_HAS_PAGE)
    public ResponseModel<Page<DictKey>> page(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "limit", defaultValue = "20") Integer limit,
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String key,
            @RequestParam(required = false) Date beginAt,
            @RequestParam(required = false) Date endAt,
            @RequestParam(required = false) String sortField,
            @RequestParam(required = false) Direction direction
    ) {
        // 自动获取当前租户ID，强制数据隔离
        Long tenantId = SecurityAccessTokenHolder.getTenantId();

        DictKeyQueryRequest query = DictKeyQueryRequest
                .builder()
                .id(id)
                .beginAt(beginAt)
                .endAt(endAt)
                .key(key)
                .page(page)
                .limit(limit)
                .direction(direction)
                .sortField(sortField)
                .build();
        Page<DictKey> response = service.page(query);
        return ResponseModel.ok(response);
    }

    /**
     * 查询单个字典key
     */
    @Operation(summary = "查询单个字典key")
    @GetMapping("/{id}")
    @PreAuthorize(HAS_ROLE_TENANT_OWNER + OR + TENANT_HAS_SINGLE)
    public ResponseModel<DictKey> get(
            @PathVariable("id") Long id
    ) {
        return ResponseModel.ok(service.findById(id));
    }

}
