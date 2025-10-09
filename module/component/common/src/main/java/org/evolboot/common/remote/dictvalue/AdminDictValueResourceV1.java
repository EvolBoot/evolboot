package org.evolboot.common.remote.dictvalue;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.annotation.AdminClient;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static org.evolboot.security.api.access.AccessAuthorities.HAS_ROLE_SUPER_ADMIN;
import static org.evolboot.security.api.access.AccessAuthorities.OR;
import static org.evolboot.common.CommonAccessAuthorities.DictValue.*;

/**
 * 字典Value
 *
 * @author evol
 * @date 2023-05-07 12:55:06
 */
@Slf4j
@RestController
@RequestMapping("/v1/admin/common/dict-value")
@Tag(name = "字典Value", description = "字典Value")
@AdminClient
public class AdminDictValueResourceV1 {

    private final DictValueAppService service;

    public AdminDictValueResourceV1(DictValueAppService service) {
        this.service = service;
    }


    @Operation(summary = "创建字典Value")
    @OperationLog("创建字典Value")
    @PostMapping("")
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN + OR + HAS_CREATE)
    public ResponseModel<?> create(
            @RequestBody @Valid
            DictValueCreateRequest request
    ) {
        DictValue dictValue = service.create(request);
        return ResponseModel.ok(new DomainId(dictValue.id()));
    }


    @Operation(summary = "删除字典Value")
    @OperationLog("删除字典Value")
    @DeleteMapping("/{id}")
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN + OR + HAS_DELETE)
    public ResponseModel<?> delete(
            @PathVariable("id") Long id
    ) {
        service.delete(id);
        return ResponseModel.ok();
    }


    @Operation(summary = "修改字典Value")
    @OperationLog("修改字典Value")
    @PutMapping
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN + OR + HAS_UPDATE)
    public ResponseModel<?> update(
            @RequestBody @Valid
            DictValueUpdateRequest request
    ) {
        service.update(request);
        return ResponseModel.ok();
    }

    @Operation(summary = "查询字典Value")
    @GetMapping("")
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN + OR + HAS_PAGE)
    public ResponseModel<Page<DictValue>> page(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "limit", defaultValue = "20") Integer limit,
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) Long dictKeyId,
            @RequestParam(required = false) String key,
            @RequestParam(required = false) Date beginAt,
            @RequestParam(required = false) Date endAt,
            @RequestParam(required = false) String sortField,
            @RequestParam(required = false) Direction direction
    ) {
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


    @Operation(summary = "查询单个字典Value")
    @GetMapping("/{id}")
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN + OR + HAS_SINGLE)
    public ResponseModel<DictValue> get(
            @PathVariable("id") Long id
    ) {
        return ResponseModel.ok(service.findById(id));
    }

    @Operation(summary = "根据Key查询字典Value")
    @GetMapping("/key/{dictKey}")
    public ResponseModel<List<DictValueAppResponse>> findByDictKey(
            @PathVariable("dictKey") String dictKey
    ) {
        return ResponseModel.ok(DictValueAppResponse.of(service.findByDictKey(dictKey)));
    }

}
