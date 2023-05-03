package org.evolboot.im.remote.groupmember;

import org.evolboot.core.annotation.ApiClient;
import org.evolboot.core.annotation.OperationLog;
import org.evolboot.core.remote.DomainId;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.im.domain.groupmember.GroupMember;
import org.evolboot.im.domain.groupmember.GroupMemberAppService;
import org.evolboot.im.domain.groupmember.DefaultGroupMemberAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.evolboot.core.data.Page;
import org.evolboot.im.domain.groupmember.GroupMemberQuery;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * 群成员
 *
 * @author evol
 * @date 2023-05-03 16:20:09
 */
@Slf4j
@RestController
@RequestMapping("/v1/api/im/group-member")
@Tag(name = "群成员", description = "群成员")
@ApiClient
public class AppGroupMemberResourceV1 {

    private final GroupMemberAppService service;

    public AppGroupMemberResourceV1(GroupMemberAppService service) {
        this.service = service;
    }


    /*

    @Operation(summary = "查询群成员")
    @GetMapping("")
    public ResponseModel<List<GroupMemberLocaleResponse>> findAll(
    ) {
        List<GroupMember> result = service.findAll();
        return ResponseModel.ok(GroupMemberLocaleResponse.of(result));
    }

    @Operation(summary = "查询群成员")
    @GetMapping("")
    public ResponseModel<Page<GroupMemberLocaleResponse>> page(
       @RequestParam(name = "page", defaultValue = "1") Integer page,
       @RequestParam(name = "limit", defaultValue = "20") Integer limit
    ) {
       GroupMemberQuery query = GroupMemberQuery
                .builder()
                .page(page)
                .limit(limit)
                .build();
        Page<GroupMember> result = service.page(query);
        return ResponseModel.ok(GroupMemberLocaleResponse.of(result));
    }

    */

}
