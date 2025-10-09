package org.evolboot.im.remote.groupapply;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.annotation.AdminClient;
import org.evolboot.core.annotation.OperationLog;
import org.evolboot.core.data.Page;
import org.evolboot.core.remote.DomainId;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.im.domain.groupapply.GroupApplyAppService;
import org.evolboot.im.domain.groupapply.GroupApplyQueryService;
import org.evolboot.im.domain.groupapply.entity.GroupApply;
import org.evolboot.im.domain.groupapply.dto.GroupApplyQueryRequest;
import org.evolboot.im.remote.groupapply.dto.GroupApplyCreateRequest;
import org.evolboot.im.remote.groupapply.dto.GroupApplyUpdateRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.Date;

import static org.evolboot.im.ImAccessAuthorities.GroupApply.*;
import static org.evolboot.security.api.access.AccessAuthorities.HAS_ROLE_SUPER_ADMIN;
import static org.evolboot.security.api.access.AccessAuthorities.OR;

/**
 * 群申请
 *
 * @author evol
 * @date 2023-05-03 17:27:04
 */
@Slf4j
@RestController
@RequestMapping("/v1/admin/im/group-apply")
@Tag(name = "群申请", description = "群申请")
@AdminClient
public class AdminGroupApplyResourceV1 {

    private final GroupApplyAppService appService;
    private final GroupApplyQueryService queryService;


    public AdminGroupApplyResourceV1(GroupApplyAppService appService, GroupApplyQueryService queryService) {
        this.appService = appService;
        this.queryService = queryService;
    }


    @Operation(summary = "创建群申请")
    @OperationLog("创建群申请")
    @PostMapping("")
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN + OR + HAS_CREATE)
    public ResponseModel<?> create(
            @RequestBody @Valid
            GroupApplyCreateRequest request
    ) {
        GroupApply groupApply = appService.create(request);
        return ResponseModel.ok(new DomainId(groupApply.id()));
    }


    @Operation(summary = "删除群申请")
    @OperationLog("删除群申请")
    @DeleteMapping("/{id}")
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN + OR + HAS_DELETE)
    public ResponseModel<?> delete(
            @PathVariable("id") Long id
    ) {
        appService.delete(id);
        return ResponseModel.ok();
    }


    @Operation(summary = "修改群申请")
    @OperationLog("修改群申请")
    @PutMapping
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN + OR + HAS_UPDATE)
    public ResponseModel<?> update(
            @RequestBody @Valid
            GroupApplyUpdateRequest request
    ) {
        appService.update(request);
        return ResponseModel.ok();
    }

    @Operation(summary = "查询群申请")
    @GetMapping("")
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN + OR + HAS_PAGE)
    public ResponseModel<Page<GroupApply>> page(
            @Parameter(description = "页数")
            @RequestParam(name = "page", defaultValue = "1") Integer page,

            @Parameter(description = "每页数量")
            @RequestParam(name = "limit", defaultValue = "20") Integer limit,

            @Parameter(description = "群申请ID")
            @RequestParam(required = false) Long id,

            @Parameter(description = "起始时间")
            @RequestParam(required = false) Date beginAt,

            @Parameter(description = "结束时间")
            @RequestParam(required = false) Date endAt,

            @Parameter(description = "搜索关键字")
            @RequestParam(required = false) String keyword
    ) {
        GroupApplyQueryRequest query = GroupApplyQueryRequest
                .builder()
                .id(id)
                .beginAt(beginAt)
                .endAt(endAt)
                .page(page)
                .limit(limit)
                .build();
        Page<GroupApply> response = queryService.page(query);
        return ResponseModel.ok(response);
    }


    @Operation(summary = "查询单个群申请")
    @GetMapping("/{id}")
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN + OR + HAS_SINGLE)
    public ResponseModel<GroupApply> get(
            @PathVariable("id") Long id
    ) {
        return ResponseModel.ok(queryService.findById(id));
    }

}
