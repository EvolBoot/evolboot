package org.evolboot.im.remote.friendapply;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.annotation.ApiClient;
import org.evolboot.core.annotation.NoRepeatSubmit;
import org.evolboot.core.annotation.OperationLog;
import org.evolboot.core.data.Page;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.im.domain.friendapply.FriendApplyQueryService;
import org.evolboot.im.domain.friendapply.entity.FriendApply;
import org.evolboot.im.domain.friendapply.FriendApplyAppService;
import org.evolboot.im.domain.friendapply.dto.FriendApplyQueryRequest;
import org.evolboot.im.remote.friendapply.dto.FriendApplyAuditRequest;
import org.evolboot.im.remote.friendapply.dto.FriendApplyCreateRequest;
import org.evolboot.security.api.SecurityAccessTokenHolder;
import org.evolboot.security.api.annotation.Authenticated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.Date;

/**
 * 好友申请
 *
 * @author evol
 * @date 2023-05-03 17:57:08
 */
@Slf4j
@RestController
@RequestMapping("/v1/api/im/friend/apply")
@Tag(name = "好友关系", description = "好友关系")
@ApiClient
public class AppFriendApplyResourceV1 {

    private final FriendApplyAppService appService;
    private final FriendApplyQueryService queryService;


    public AppFriendApplyResourceV1(FriendApplyAppService appService, FriendApplyQueryService queryService) {
        this.appService = appService;
        this.queryService = queryService;
    }


    @Operation(summary = "查询好友申请列表")
    @GetMapping("")
    @Authenticated
    public ResponseModel<Page<FriendApply>> page(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "limit", defaultValue = "20") Integer limit,
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) Date beginAt,
            @RequestParam(required = false) Date endAt
    ) {
        FriendApplyQueryRequest query = FriendApplyQueryRequest
                .builder()
                .id(id)
                .beginAt(beginAt)
                .endAt(endAt)
                .page(page)
                .limit(limit)
                .toUserId(SecurityAccessTokenHolder.getUserId())
                .build();
        Page<FriendApply> response = queryService.page(query);
        return ResponseModel.ok(response);
    }

    @Operation(summary = "好友申请")
    @OperationLog("好友申请")
    @PostMapping("")
    @Authenticated
    @NoRepeatSubmit
    public ResponseModel<FriendApply> create(
            @RequestBody @Valid
            FriendApplyCreateRequest request
    ) {
        return ResponseModel.ok(appService.create(request.to(SecurityAccessTokenHolder.getUserId())));
    }

    @Operation(summary = "好友申请审核")
    @OperationLog("好友申请")
    @PostMapping("/audit")
    @Authenticated
    @NoRepeatSubmit
    public ResponseModel<FriendApply> audit(
            @RequestBody @Valid
            FriendApplyAuditRequest request
    ) {
        return ResponseModel.ok(appService.audit(request.to(SecurityAccessTokenHolder.getUserId())));
    }


    /*

    @Operation(summary = "查询好友申请")
    @GetMapping("")
    public ResponseModel<List<FriendApplyLocaleResponse>> findAll(
    ) {
        List<FriendApply> result = service.findAll();
        return ResponseModel.ok(FriendApplyLocaleResponse.of(result));
    }

    @Operation(summary = "查询好友申请")
    @GetMapping("")
    public ResponseModel<Page<FriendApplyLocaleResponse>> page(
       @RequestParam(name = "page", defaultValue = "1") Integer page,
       @RequestParam(name = "limit", defaultValue = "20") Integer limit
    ) {
       FriendApplyQuery query = FriendApplyQuery
                .builder()
                .page(page)
                .limit(limit)
                .build();
        Page<FriendApply> result = service.page(query);
        return ResponseModel.ok(FriendApplyLocaleResponse.of(result));
    }

    */

}
