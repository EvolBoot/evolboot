package org.evolboot.im.remote.groupapply;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.annotation.ApiClient;
import org.evolboot.im.domain.groupapply.GroupApplyAppService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
