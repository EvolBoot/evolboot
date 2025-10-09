package org.evolboot.im.remote.friend;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.annotation.ApiClient;
import org.evolboot.core.annotation.OperationLog;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.im.domain.friend.FriendAppService;
import org.evolboot.im.domain.friend.FriendQueryService;
import org.evolboot.security.api.SecurityAccessTokenHolder;
import org.evolboot.security.api.annotation.Authenticated;
import org.springframework.web.bind.annotation.*;

/**
 * 好友关系
 *
 * @author evol
 * @date 2023-05-03 17:40:14
 */
@Slf4j
@RestController
@RequestMapping("/v1/api/im/friend")
@Tag(name = "好友关系", description = "好友关系")
@ApiClient
public class AppFriendResourceV1 {

    private final FriendAppService service;

    private final FriendQueryService queryService;


    public AppFriendResourceV1(FriendAppService service, FriendQueryService queryService) {
        this.service = service;
        this.queryService = queryService;
    }


    @Operation(summary = "删除好友关系")
    @OperationLog("删除好友关系")
    @DeleteMapping("/friend-user-id/{friendUserId}")
    @Authenticated
    public ResponseModel<?> delete(
            @PathVariable("friendUserId") Long friendUserId
    ) {
        service.deleteByFriendUserId(SecurityAccessTokenHolder.getUserId(), friendUserId);
        return ResponseModel.ok();
    }


    @Operation(summary = "将好友拉入黑名单")
    @OperationLog("将好友拉入黑名单")
    @PutMapping("/join-blacklist/friend-user-id/{friendUserId}")
    @Authenticated
    public ResponseModel<?> joinBlacklist(
            @PathVariable("friendUserId") Long friendUserId
    ) {
        service.joinBlacklist(SecurityAccessTokenHolder.getUserId(), friendUserId);
        return ResponseModel.ok();
    }


    @Operation(summary = "将好友移出黑名单")
    @OperationLog("将好友移出黑名单")
    @PutMapping("/remove-blacklist/friend-user-id/{friendUserId}")
    @Authenticated
    public ResponseModel<?> removeBlacklist(
            @PathVariable("friendUserId") Long friendUserId
    ) {
        service.removeBlacklist(SecurityAccessTokenHolder.getUserId(), friendUserId);
        return ResponseModel.ok();
    }



    /*

    @Operation(summary = "查询好友关系")
    @GetMapping("")
    public ResponseModel<List<FriendLocaleResponse>> findAll(
    ) {
        List<Friend> result = service.findAll();
        return ResponseModel.ok(FriendLocaleResponse.of(result));
    }

    @Operation(summary = "查询好友关系")
    @GetMapping("")
    public ResponseModel<Page<FriendLocaleResponse>> page(
       @RequestParam(name = "page", defaultValue = "1") Integer page,
       @RequestParam(name = "limit", defaultValue = "20") Integer limit
    ) {
       FriendQuery query = FriendQuery
                .builder()
                .page(page)
                .limit(limit)
                .build();
        Page<Friend> result = service.page(query);
        return ResponseModel.ok(FriendLocaleResponse.of(result));
    }

    */

}
