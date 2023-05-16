package org.evolboot.im.remote.group;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.annotation.ApiClient;
import org.evolboot.core.annotation.OperationLog;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.im.domain.group.Group;
import org.evolboot.im.domain.group.GroupAppService;
import org.evolboot.security.api.SecurityAccessTokenHolder;
import org.evolboot.security.api.annotation.Authenticated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 群组
 *
 * @author evol
 * @date 2023-05-03 15:52:47
 */
@Slf4j
@RestController
@RequestMapping("/v1/api/im/group")
@Tag(name = "群组", description = "群组")
@ApiClient
public class AppGroupResourceV1 {

    private final GroupAppService service;

    public AppGroupResourceV1(GroupAppService service) {
        this.service = service;
    }


    @Operation(summary = "创建群组")
    @OperationLog("创建群组")
    @PostMapping("")
    @Authenticated
    public ResponseModel<Group> create(
            @RequestBody @Valid
            GroupCreateRequest request
    ) {
        Group group = service.create(request.to(SecurityAccessTokenHolder.getPrincipalId()));
        return ResponseModel.ok(group);
    }


    /*

    @Operation(summary = "查询群组")
    @GetMapping("")
    public ResponseModel<List<GroupLocaleResponse>> findAll(
    ) {
        List<Group> result = service.findAll();
        return ResponseModel.ok(GroupLocaleResponse.of(result));
    }

    @Operation(summary = "查询群组")
    @GetMapping("")
    public ResponseModel<Page<GroupLocaleResponse>> page(
       @RequestParam(name = "page", defaultValue = "1") Integer page,
       @RequestParam(name = "limit", defaultValue = "20") Integer limit
    ) {
       GroupQuery query = GroupQuery
                .builder()
                .page(page)
                .limit(limit)
                .build();
        Page<Group> result = service.page(query);
        return ResponseModel.ok(GroupLocaleResponse.of(result));
    }

    */

}
