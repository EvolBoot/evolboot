package org.evolboot.im.remote.groupmember;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.annotation.AdminClient;
import org.evolboot.core.data.Page;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.im.domain.groupmember.GroupMemberQueryService;
import org.evolboot.im.domain.groupmember.entity.GroupMember;
import org.evolboot.im.domain.groupmember.GroupMemberAppService;
import org.evolboot.im.domain.groupmember.dto.GroupMemberQueryRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static org.evolboot.im.ImAccessAuthorities.GroupMember.HAS_PAGE;
import static org.evolboot.im.ImAccessAuthorities.GroupMember.HAS_SINGLE;
import static org.evolboot.security.api.access.AccessAuthorities.HAS_ROLE_ADMIN;
import static org.evolboot.security.api.access.AccessAuthorities.OR;

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
    private final GroupMemberQueryService queryService;

    public AdminGroupMemberResourceV1(GroupMemberAppService service, GroupMemberQueryService queryService) {
        this.service = service;
        this.queryService = queryService;
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
    @PutMapping
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_UPDATE)
    public ResponseModel<?> update(
            @RequestBody @Valid
                    GroupMemberUpdateRequest request
    ) {
        service.update(request);
        return ResponseModel.ok();
    }
*/

    @Operation(summary = "查询群成员")
    @GetMapping("")
    @PreAuthorize(HAS_ROLE_ADMIN + OR + HAS_PAGE)
    public ResponseModel<Page<GroupMember>> page(
            @Parameter(description = "页数")
            @RequestParam(name = "page", defaultValue = "1") Integer page,

            @Parameter(description = "每页数量")
            @RequestParam(name = "limit", defaultValue = "20") Integer limit,

            @Parameter(description = "群成员ID")
            @RequestParam(required = false) Long id,

            @Parameter(description = "起始时间")
            @RequestParam(required = false) Date startDate,

            @Parameter(description = "结束时间")
            @RequestParam(required = false) Date endDate,

            @Parameter(description = "搜索关键字")
            @RequestParam(required = false) String keyword
    ) {
        GroupMemberQueryRequest query = GroupMemberQueryRequest
                .builder()
                .id(id)
                .startDate(startDate)
                .endDate(endDate)
                .page(page)
                .limit(limit)
                .build();
        Page<GroupMember> response = queryService.page(query);
        return ResponseModel.ok(response);
    }


    @Operation(summary = "查询单个群成员")
    @GetMapping("/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN + OR + HAS_SINGLE)
    public ResponseModel<GroupMember> get(
            @PathVariable("id") Long id
    ) {
        return ResponseModel.ok(queryService.findById(id));
    }

}
