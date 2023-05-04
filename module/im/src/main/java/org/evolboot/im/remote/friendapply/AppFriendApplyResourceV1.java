package org.evolboot.im.remote.friendapply;

import org.evolboot.core.annotation.ApiClient;
import org.evolboot.core.annotation.NoRepeatSubmit;
import org.evolboot.core.annotation.OperationLog;
import org.evolboot.core.remote.DomainId;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.im.domain.friendapply.FriendApply;
import org.evolboot.im.domain.friendapply.FriendApplyAppService;
import org.evolboot.im.domain.friendapply.DefaultFriendApplyAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.evolboot.security.api.SecurityAccessTokenHolder;
import org.evolboot.security.api.annotation.Authenticated;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.evolboot.core.data.Page;
import org.evolboot.im.domain.friendapply.FriendApplyQuery;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;

import static org.evolboot.im.ImAccessAuthorities.FriendApply.HAS_CREATE;
import static org.evolboot.security.api.access.AccessAuthorities.HAS_ROLE_ADMIN;
import static org.evolboot.security.api.access.AccessAuthorities.or;

/**
 * 好友申请
 *
 * @author evol
 * @date 2023-05-03 17:57:08
 */
@Slf4j
@RestController
@RequestMapping("/v1/api/im/friend-apply")
@Tag(name = "好友申请", description = "好友申请")
@ApiClient
public class AppFriendApplyResourceV1 {

    private final FriendApplyAppService service;

    public AppFriendApplyResourceV1(FriendApplyAppService service) {
        this.service = service;
    }


    @Operation(summary = "好友申请")
    @OperationLog("好友申请")
    @PostMapping("")
    @Authenticated
    @NoRepeatSubmit
    public ResponseModel<?> create(
            @RequestBody @Valid
            FriendApplyCreateRequest request
    ) {
        FriendApply friendApply = service.create(request.to(SecurityAccessTokenHolder.getPrincipalId()));
        return ResponseModel.ok(new DomainId(friendApply.id()));
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
