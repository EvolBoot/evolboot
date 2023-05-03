package org.evolboot.im.remote.groupapply;

import org.evolboot.core.annotation.AdminClient;
import org.evolboot.core.annotation.OperationLog;
import org.evolboot.core.remote.DomainId;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.im.domain.groupapply.GroupApply;
import org.evolboot.im.domain.groupapply.GroupApplyAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.evolboot.im.domain.groupapply.GroupApplyQuery;
import org.evolboot.core.data.Page;

import javax.validation.Valid;
import java.util.List;

import static org.evolboot.security.api.access.AccessAuthorities.*;
import static org.evolboot.im.ImAccessAuthorities.GroupApply.*;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;

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

    private final GroupApplyAppService service;

    public AdminGroupApplyResourceV1(GroupApplyAppService service) {
        this.service = service;
    }


    @Operation(summary = "创建群申请")
    @OperationLog("创建群申请")
    @PostMapping("")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_CREATE)
    public ResponseModel<?> create(
            @RequestBody @Valid
                    GroupApplyCreateRequest request
    ) {
        GroupApply groupApply = service.create(request);
        return ResponseModel.ok(new DomainId(groupApply.id()));
    }


    @Operation(summary = "删除群申请")
    @OperationLog("删除群申请")
    @DeleteMapping("/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_DELETE)
    public ResponseModel<?> delete(
            @PathVariable("id") Long id
    ) {
        service.delete(id);
        return ResponseModel.ok();
    }


    @Operation(summary = "修改群申请")
    @OperationLog("修改群申请")
    @PutMapping("/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_UPDATE)
    public ResponseModel<?> update(
            @PathVariable("id") Long id,
            @RequestBody @Valid
                    GroupApplyUpdateRequest request
    ) {
        service.update(id, request);
        return ResponseModel.ok();
    }

    @Operation(summary = "查询群申请")
    @GetMapping("")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_PAGE)
    public ResponseModel<Page<GroupApply>> page(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "limit", defaultValue = "20") Integer limit,
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) Date startDate,
            @RequestParam(required = false) Date endDate
    ) {
        GroupApplyQuery query = GroupApplyQuery
                .builder()
                .id(id)
                .startDate(startDate)
                .endDate(endDate)
                .page(page)
                .limit(limit)
                .build();
        Page<GroupApply> response = service.page(query);
        return ResponseModel.ok(response);
    }


    @Operation(summary = "查询单个群申请")
    @GetMapping("/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_SINGLE)
    public ResponseModel<GroupApply> get(
            @PathVariable("id") Long id
    ) {
        return ResponseModel.ok(service.findById(id));
    }

}
