package org.evolboot.im.remote.friend;

import org.evolboot.core.annotation.AdminClient;
import org.evolboot.core.annotation.OperationLog;
import org.evolboot.core.remote.DomainId;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.im.domain.friend.Friend;
import org.evolboot.im.domain.friend.FriendAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.evolboot.im.domain.friend.FriendQuery;
import org.evolboot.core.data.Page;

import javax.validation.Valid;
import java.util.List;

import static org.evolboot.security.api.access.AccessAuthorities.*;
import static org.evolboot.im.ImAccessAuthorities.Friend.*;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * 好友关系
 *
 * @author evol
 * @date 2023-05-03 17:40:14
 */
@Slf4j
@RestController
@RequestMapping("/v1/admin/im/friend")
@Tag(name = "好友关系", description = "好友关系")
@AdminClient
public class AdminFriendResourceV1 {

    private final FriendAppService service;

    public AdminFriendResourceV1(FriendAppService service) {
        this.service = service;
    }

//
//    @Operation(summary = "创建好友关系")
//    @OperationLog("创建好友关系")
//    @PostMapping("")
//    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_CREATE)
//    public ResponseModel<?> create(
//            @RequestBody @Valid
//                    FriendCreateRequest request
//    ) {
//        Friend friend = service.create(request);
//        return ResponseModel.ok(new DomainId(friend.id()));
//    }
//
//
//    @Operation(summary = "删除好友关系")
//    @OperationLog("删除好友关系")
//    @DeleteMapping("/{id}")
//    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_DELETE)
//    public ResponseModel<?> delete(
//            @PathVariable("id") Long id
//    ) {
//        service.delete(id);
//        return ResponseModel.ok();
//    }
//
//
//    @Operation(summary = "修改好友关系")
//    @OperationLog("修改好友关系")
//    @PutMapping("/{id}")
//    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_UPDATE)
//    public ResponseModel<?> update(
//            @PathVariable("id") Long id,
//            @RequestBody @Valid
//                    FriendUpdateRequest request
//    ) {
//        service.update(id, request);
//        return ResponseModel.ok();
//    }

    @Operation(summary = "查询好友关系")
    @GetMapping("")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_PAGE)
    public ResponseModel<Page<Friend>> page(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "limit", defaultValue = "20") Integer limit,
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) Date startDate,
            @RequestParam(required = false) Date endDate
    ) {
        FriendQuery query = FriendQuery
                .builder()
                .id(id)
                .endDate(endDate)
                .page(page)
                .limit(limit)
                .build();
        Page<Friend> response = service.page(query);
        return ResponseModel.ok(response);
    }


    @Operation(summary = "查询单个好友关系")
    @GetMapping("/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_SINGLE)
    public ResponseModel<Friend> get(
            @PathVariable("id") Long id
    ) {
        return ResponseModel.ok(service.findById(id));
    }

}
