package org.evolboot.common.remote.dictvalue;

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
import org.evolboot.common.domain.dictvalue.DictValueAppService;
import org.evolboot.common.domain.dictvalue.entity.DictValue;
import org.evolboot.common.domain.dictvalue.dto.DictValueQueryRequest;
import org.evolboot.common.remote.dictvalue.dto.DictValueAppResponse;
import org.evolboot.common.remote.dictvalue.dto.DictValueCreateRequest;
import org.evolboot.common.remote.dictvalue.dto.DictValueUpdateRequest;
import org.evolboot.security.api.SecurityAccessTokenHolder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static org.evolboot.security.api.access.AccessAuthorities.HAS_ROLE_TENANT_OWNER;
import static org.evolboot.security.api.access.AccessAuthorities.OR;
import static org.evolboot.common.CommonAccessAuthorities.DictValue.*;

/**
 * Tenant Dict Value Resource
 * 租户字典Value管理 - 租户所有者和租户员工可访问
 * 管理租户自定义字典值
 */
@Slf4j
@RestController
@RequestMapping("/tenant/v1/common/dict-value")
@Tag(name = "租户字典Value", description = "租户字典Value")
@TenantClient
public class TenantDictValueResourceV1 {

    private final DictValueAppService service;

    public TenantDictValueResourceV1(DictValueAppService service) {
        this.service = service;
    }

    /**
     * 创建字典Value
     */
    @Operation(summary = "创建字典Value")
    @OperationLog("创建字典Value")
    @PostMapping("")
    @PreAuthorize(HAS_ROLE_TENANT_OWNER + OR + TENANT_HAS_CREATE)
    public ResponseModel<?> create(
            @RequestBody @Valid DictValueCreateRequest request
    ) {
        DictValue dictValue = service.create(request);
        return ResponseModel.ok(new DomainId(dictValue.id()));
    }

    /**
     * 删除字典Value
     */
    @Operation(summary = "删除字典Value")
    @OperationLog("删除字典Value")
    @DeleteMapping("/{id}")
    @PreAuthorize(HAS_ROLE_TENANT_OWNER + OR + TENANT_HAS_DELETE)
    public ResponseModel<?> delete(
            @PathVariable("id") Long id
    ) {
        service.delete(id);
        return ResponseModel.ok();
    }

    /**
     * 修改字典Value
     */
    @Operation(summary = "修改字典Value")
    @OperationLog("修改字典Value")
    @PutMapping
    @PreAuthorize(HAS_ROLE_TENANT_OWNER + OR + TENANT_HAS_UPDATE)
    public ResponseModel<?> update(
            @RequestBody @Valid DictValueUpdateRequest request
    ) {
        service.update(request);
        return ResponseModel.ok();
    }

    /**
     * 查询字典Value
     * 自动过滤本租户的字典值
     */
    @Operation(summary = "查询字典Value")
    @GetMapping("")
    @PreAuthorize(HAS_ROLE_TENANT_OWNER + OR + TENANT_HAS_PAGE)
    public ResponseModel<Page<DictValue>> page(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "limit", defaultValue = "20") Integer limit,
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) Long dictKeyId,
            @RequestParam(required = false) String key,
            @RequestParam(required = false) LocalDateTime beginAt,
            @RequestParam(required = false) LocalDateTime endAt,
            @RequestParam(required = false) String sortField,
            @RequestParam(required = false) Direction direction
    ) {
        // 自动获取当前租户ID，强制数据隔离
        Long tenantId = SecurityAccessTokenHolder.getTenantId();

        DictValueQueryRequest query = DictValueQueryRequest
                .builder()
                .id(id)
                .beginAt(beginAt)
                .endAt(endAt)
                .page(page)
                .dictKeyId(dictKeyId)
                .direction(direction)
                .sortField(sortField)
                .key(key)
                .limit(limit)
                .build();
        Page<DictValue> response = service.page(query);
        return ResponseModel.ok(response);
    }

    /**
     * 查询单个字典Value
     */
    @Operation(summary = "查询单个字典Value")
    @GetMapping("/{id}")
    @PreAuthorize(HAS_ROLE_TENANT_OWNER + OR + TENANT_HAS_SINGLE)
    public ResponseModel<DictValue> get(
            @PathVariable("id") Long id
    ) {
        return ResponseModel.ok(service.findById(id));
    }

    /**
     * 根据Key查询字典Value
     */
    @Operation(summary = "根据Key查询字典Value")
    @GetMapping("/key/{dictKey}")
    @PreAuthorize(HAS_ROLE_TENANT_OWNER + OR + TENANT_HAS_PAGE)
    public ResponseModel<List<DictValueAppResponse>> findByDictKey(
            @PathVariable("dictKey") String dictKey
    ) {
        return ResponseModel.ok(DictValueAppResponse.of(service.findByDictKey(dictKey)));
    }

}
