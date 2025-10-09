package org.evolboot.im.remote.friendapply;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.annotation.AdminClient;
import org.evolboot.core.data.Page;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.im.domain.friendapply.FriendApplyQueryService;
import org.evolboot.im.domain.friendapply.entity.FriendApply;
import org.evolboot.im.domain.friendapply.FriendApplyAppService;
import org.evolboot.im.domain.friendapply.dto.FriendApplyQueryRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static org.evolboot.im.ImAccessAuthorities.FriendApply.HAS_PAGE;
import static org.evolboot.im.ImAccessAuthorities.FriendApply.HAS_SINGLE;
import static org.evolboot.security.api.access.AccessAuthorities.HAS_ROLE_SUPER_ADMIN;
import static org.evolboot.security.api.access.AccessAuthorities.OR;

/**
 * 好友申请
 *
 * @author evol
 * @date 2023-05-03 17:57:08
 */
@Slf4j
@RestController
@RequestMapping("/admin/v1/im/friend-apply")
@Tag(name = "好友关系", description = "好友关系")
@AdminClient
public class AdminFriendApplyResourceV1 {

    private final FriendApplyAppService appService;
    private final FriendApplyQueryService queryService;

    public AdminFriendApplyResourceV1(FriendApplyAppService appService, FriendApplyQueryService queryService) {
        this.appService = appService;
        this.queryService = queryService;
    }


/*
    @Operation(summary = "创建好友申请")
    @OperationLog("创建好友申请")
    @PostMapping("")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_CREATE)
    public ResponseModel<?> create(
            @RequestBody @Valid
                    FriendApplyCreateRequest request
    ) {
        FriendApply friendApply = service.create(request.to(Se));
        return ResponseModel.ok(new DomainId(friendApply.id()));
    }
*/
/*

    @Operation(summary = "删除好友申请")
    @OperationLog("删除好友申请")
    @DeleteMapping("/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_DELETE)
    public ResponseModel<?> delete(
            @PathVariable("id") Long id
    ) {
        service.delete(id);
        return ResponseModel.ok();
    }


 */

    @Operation(summary = "查询好友申请列表")
    @GetMapping("")
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN + OR + HAS_PAGE)
    public ResponseModel<Page<FriendApply>> page(
            @Parameter(description = "页数")
            @RequestParam(name = "page", defaultValue = "1") Integer page,

            @Parameter(description = "每页数量")
            @RequestParam(name = "limit", defaultValue = "20") Integer limit,

            @Parameter(description = "好友申请列表ID")
            @RequestParam(required = false) Long id,

            @Parameter(description = "起始时间")
            @RequestParam(required = false) Date beginAt,

            @Parameter(description = "结束时间")
            @RequestParam(required = false) Date endAt,

            @Parameter(description = "搜索关键字")
            @RequestParam(required = false) String keyword
    ) {
        FriendApplyQueryRequest query = FriendApplyQueryRequest
                .builder()
                .id(id)
                .beginAt(beginAt)
                .endAt(endAt)
                .page(page)
                .limit(limit)
                .build();
        Page<FriendApply> response = queryService.page(query);
        return ResponseModel.ok(response);
    }


    @Operation(summary = "查询单个好友申请")
    @GetMapping("/{id}")
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN + OR + HAS_SINGLE)
    public ResponseModel<FriendApply> get(
            @PathVariable("id") Long id
    ) {
        return ResponseModel.ok(queryService.findById(id));
    }

}
