package org.evolboot.im.remote.groupmember;

import org.evolboot.core.annotation.AdminClient;
import org.evolboot.core.annotation.OperationLog;
import org.evolboot.core.remote.DomainId;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.im.domain.groupmember.GroupMember;
import org.evolboot.im.domain.groupmember.GroupMemberAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.evolboot.im.domain.groupmember.GroupMemberQuery;
import org.evolboot.core.data.Page;

import javax.validation.Valid;
import java.util.List;

import static org.evolboot.security.api.access.AccessAuthorities.*;
import static org.evolboot.im.ImAccessAuthorities.GroupMember.*;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * 群成员
 *
 * @author evol
 * @date 2023-05-03 16:20:09
 */
@Slf4j
@RestController
@RequestMapping("/v1/admin/im/group-member")
@Tag(name = "群成员", description = "群成员")
@AdminClient
public class AdminGroupMemberResourceV1 {

    private final GroupMemberAppService service;

    public AdminGroupMemberResourceV1(GroupMemberAppService service) {
        this.service = service;
    }

/*

    @Operation(summary = "创建群成员")
    @OperationLog("创建群成员")
    @PostMapping("")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_CREATE)
    public ResponseModel<?> create(
            @RequestBody @Valid
                    GroupMemberCreateRequest request
    ) {
        GroupMember groupMember = service.create(request);
        return ResponseModel.ok(new DomainId(groupMember.id()));
    }


    @Operation(summary = "删除群成员")
    @OperationLog("删除群成员")
    @DeleteMapping("/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_DELETE)
    public ResponseModel<?> delete(
            @PathVariable("id") Long id
    ) {
        service.delete(id);
        return ResponseModel.ok();
    }


    @Operation(summary = "修改群成员")
    @OperationLog("修改群成员")
    @PutMapping("/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_UPDATE)
    public ResponseModel<?> update(
            @PathVariable("id") Long id,
            @RequestBody @Valid
                    GroupMemberUpdateRequest request
    ) {
        service.update(id, request);
        return ResponseModel.ok();
    }
*/

    @Operation(summary = "查询群成员")
    @GetMapping("")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_PAGE)
    public ResponseModel<Page<GroupMember>> page(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "limit", defaultValue = "20") Integer limit,
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) Date startDate,
            @RequestParam(required = false) Date endDate
    ) {
        GroupMemberQuery query = GroupMemberQuery
                .builder()
                .id(id)
                .startDate(startDate)
                .endDate(endDate)
                .page(page)
                .limit(limit)
                .build();
        Page<GroupMember> response = service.page(query);
        return ResponseModel.ok(response);
    }


    @Operation(summary = "查询单个群成员")
    @GetMapping("/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_SINGLE)
    public ResponseModel<GroupMember> get(
            @PathVariable("id") Long id
    ) {
        return ResponseModel.ok(service.findById(id));
    }

}
