package org.evolboot.system.remote.operationlog;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.evolboot.core.annotation.AdminClient;
import org.evolboot.core.data.Page;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.system.domain.operationlog.entity.OperationLog;
import org.evolboot.system.domain.operationlog.OperationLogAppService;
import org.evolboot.system.domain.operationlog.service.OperationLogQuery;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static org.evolboot.security.api.access.AccessAuthorities.HAS_ROLE_ADMIN;
import static org.evolboot.security.api.access.AccessAuthorities.or;
import static org.evolboot.system.SystemAccessAuthorities.OperationLog.HAS_PAGE;
import static org.evolboot.system.SystemAccessAuthorities.OperationLog.HAS_SINGLE;


/**
 * @author evol
 */
@RestController
@RequestMapping("/v1/admin/system/operation-log")
@Tag(name = "操作日志管理", description = "操作日志管理")
@AdminClient
public class AdminOperationLogResourceV1 {

    private final OperationLogAppService service;

    public AdminOperationLogResourceV1(OperationLogAppService service) {
        this.service = service;
    }

/*
    @Operation(summary = "创建操作日志")
    @OperationLog("创建操作日志")
    @PostMapping("/operation-logs")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_CREATE)
    public ResponseModel<?> create(
            @RequestBody @Valid
                    OperationLogCreateRequest request
    ) {
        Long id = service.create(request);
        return ResponseModel.ok(new DomainId(id));
    }


    @Operation(summary = "删除操作日志")
    @OperationLog("删除操作日志")
    @DeleteMapping("/operation-logs/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_DELETE)
    public ResponseModel<?> delete(
            @PathVariable("id") Long id
    ) {
        service.delete(id);
        return ResponseModel.ok();
    }


    @Operation(summary = "修改操作日志")
    @OperationLog("修改操作日志")
    @PutMapping("/operation-logs/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_UPDATE)
    public ResponseModel<?> update(
            @PathVariable("id") Long id,
            @RequestBody @Valid
                    OperationLogUpdateRequest request
    ) {
        service.update(id, request);
        return ResponseModel.ok();
    }

    */

    @Operation(summary = "查询操作日志")
    @GetMapping
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_PAGE)
    public ResponseModel<Page<OperationLog>> page(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "limit", defaultValue = "20") Integer limit,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @RequestParam(required = false) Date begin,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @RequestParam(required = false) Date end,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) String operation
    ) {
        OperationLogQuery query = OperationLogQuery
                .builder()
                .begin(begin)
                .end(end)
                .page(page)
                .limit(limit)
                .userId(userId)
                .operation(operation)
                .build();
        Page<OperationLog> response = service.page(query);
        return ResponseModel.ok(response);
    }


    @Operation(summary = "查询单个操作日志")
    @GetMapping("/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_SINGLE)
    public ResponseModel<OperationLog> get(
            @PathVariable("id") Long id
    ) {
        return ResponseModel.ok(service.findById(id));
    }

}
