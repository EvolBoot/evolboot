package org.evolboot.common.remote.dictkey;

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
import org.evolboot.common.domain.dictkey.DictKeyAppService;
import org.evolboot.common.domain.dictkey.entity.DictKey;
import org.evolboot.common.domain.dictkey.service.DictKeyQuery;
import org.evolboot.common.remote.dictkey.dto.DictKeyCreateRequest;
import org.evolboot.common.remote.dictkey.dto.DictKeyUpdateRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Set;

import static org.evolboot.security.api.access.AccessAuthorities.HAS_ROLE_ADMIN;
import static org.evolboot.security.api.access.AccessAuthorities.OR;
import static org.evolboot.common.CommonAccessAuthorities.DictKey.*;

/**
 * 字典key
 *
 * @author evol
 * @date 2023-05-07 12:29:33
 */
@Slf4j
@RestController
@RequestMapping("/v1/admin/common/dict-key")
@Tag(name = "字典key", description = "字典key")
@AdminClient
public class AdminDictKeyResourceV1 {

    private final DictKeyAppService service;

    public AdminDictKeyResourceV1(DictKeyAppService service) {
        this.service = service;
    }


    @Operation(summary = "创建字典key")
    @OperationLog("创建字典key")
    @PostMapping("")
    @PreAuthorize(HAS_ROLE_ADMIN + OR + HAS_CREATE)
    public ResponseModel<?> create(
            @RequestBody @Valid
            DictKeyCreateRequest request
    ) {
        DictKey dictKey = service.create(request);
        return ResponseModel.ok(new DomainId(dictKey.id()));
    }


    @Operation(summary = "删除字典key")
    @OperationLog("删除字典key")
    @DeleteMapping("/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN + OR + HAS_DELETE)
    public ResponseModel<?> delete(
            @PathVariable("id") Long id
    ) {
        service.delete(id);
        return ResponseModel.ok();
    }

    @Operation(summary = "删除字典key")
    @OperationLog("删除字典key")
    @DeleteMapping()
    @PreAuthorize(HAS_ROLE_ADMIN + OR + HAS_DELETE)
    public ResponseModel<?> delete(
            @RequestBody Set<Long> ids
    ) {
        service.delete(ids);
        return ResponseModel.ok();
    }


    @Operation(summary = "修改字典key")
    @OperationLog("修改字典key")
    @PutMapping
    @PreAuthorize(HAS_ROLE_ADMIN + OR + HAS_UPDATE)
    public ResponseModel<?> update(
            @RequestBody @Valid
            DictKeyUpdateRequest request
    ) {
        service.update(request);
        return ResponseModel.ok();
    }

    @Operation(summary = "查询字典key")
    @GetMapping("")
    @PreAuthorize(HAS_ROLE_ADMIN + OR + HAS_PAGE)
    public ResponseModel<Page<DictKey>> page(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "limit", defaultValue = "20") Integer limit,
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String key,
            @RequestParam(required = false) Date startDate,
            @RequestParam(required = false) Date endDate,
            @RequestParam(required = false) String orderField,
            @RequestParam(required = false) Direction order
    ) {
        DictKeyQuery query = DictKeyQuery
                .builder()
                .id(id)
                .startDate(startDate)
                .endDate(endDate)
                .key(key)
                .page(page)
                .limit(limit)
                .order(order)
                .orderField(orderField)
                .build();
        Page<DictKey> response = service.page(query);
        return ResponseModel.ok(response);
    }


    @Operation(summary = "查询单个字典key")
    @GetMapping("/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN + OR + HAS_SINGLE)
    public ResponseModel<DictKey> get(
            @PathVariable("id") Long id
    ) {
        return ResponseModel.ok(service.findById(id));
    }

}
