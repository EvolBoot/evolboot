package org.evolboot.im.remote.groupapply;

import org.evolboot.core.annotation.ApiClient;
import org.evolboot.core.annotation.OperationLog;
import org.evolboot.core.remote.DomainId;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.im.domain.groupapply.GroupApply;
import org.evolboot.im.domain.groupapply.GroupApplyAppService;
import org.evolboot.im.domain.groupapply.DefaultGroupApplyAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.evolboot.core.data.Page;
import org.evolboot.im.domain.groupapply.GroupApplyQuery;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * 群申请
 *
 * @author evol
 * @date 2023-05-03 17:27:04
 */
@Slf4j
@RestController
@RequestMapping("/v1/api/im/group-apply")
@Tag(name = "群申请", description = "群申请")
@ApiClient
public class AppGroupApplyResourceV1 {

    private final GroupApplyAppService service;

    public AppGroupApplyResourceV1(GroupApplyAppService service) {
        this.service = service;
    }


    /*

    @Operation(summary = "查询群申请")
    @GetMapping("")
    public ResponseModel<List<GroupApplyLocaleResponse>> findAll(
    ) {
        List<GroupApply> result = service.findAll();
        return ResponseModel.ok(GroupApplyLocaleResponse.of(result));
    }

    @Operation(summary = "查询群申请")
    @GetMapping("")
    public ResponseModel<Page<GroupApplyLocaleResponse>> page(
       @RequestParam(name = "page", defaultValue = "1") Integer page,
       @RequestParam(name = "limit", defaultValue = "20") Integer limit
    ) {
       GroupApplyQuery query = GroupApplyQuery
                .builder()
                .page(page)
                .limit(limit)
                .build();
        Page<GroupApply> result = service.page(query);
        return ResponseModel.ok(GroupApplyLocaleResponse.of(result));
    }

    */

}
