package org.evolboot.im.remote.friend;

import org.evolboot.core.annotation.ApiClient;
import org.evolboot.core.annotation.NoRepeatSubmit;
import org.evolboot.core.annotation.OperationLog;
import org.evolboot.core.lang.BusinessResult;
import org.evolboot.core.remote.DomainId;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.im.domain.friend.Friend;
import org.evolboot.im.domain.friend.FriendAppService;
import org.evolboot.im.domain.friend.DefaultFriendAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.evolboot.im.domain.friendapply.FriendApply;
import org.evolboot.security.api.SecurityAccessTokenHolder;
import org.evolboot.security.api.annotation.Authenticated;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.evolboot.core.data.Page;
import org.evolboot.im.domain.friend.FriendQuery;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;

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

    public AppFriendResourceV1(FriendAppService service) {
        this.service = service;
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
