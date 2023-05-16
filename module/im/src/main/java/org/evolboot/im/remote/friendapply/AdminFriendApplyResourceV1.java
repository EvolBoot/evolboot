package org.evolboot.im.remote.friendapply;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.annotation.AdminClient;
import org.evolboot.core.data.Page;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.im.domain.friendapply.FriendApply;
import org.evolboot.im.domain.friendapply.FriendApplyAppService;
import org.evolboot.im.domain.friendapply.FriendApplyQuery;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static org.evolboot.im.ImAccessAuthorities.FriendApply.HAS_PAGE;
import static org.evolboot.im.ImAccessAuthorities.FriendApply.HAS_SINGLE;
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
@RequestMapping("/v1/admin/im/friend-apply")
@Tag(name = "好友关系", description = "好友关系")
@AdminClient
public class AdminFriendApplyResourceV1 {

    private final FriendApplyAppService service;

    public AdminFriendApplyResourceV1(FriendApplyAppService service) {
        this.service = service;
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
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_PAGE)
    public ResponseModel<Page<FriendApply>> page(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "limit", defaultValue = "20") Integer limit,
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) Date startDate,
            @RequestParam(required = false) Date endDate
    ) {
        FriendApplyQuery query = FriendApplyQuery
                .builder()
                .id(id)
                .startDate(startDate)
                .endDate(endDate)
                .page(page)
                .limit(limit)
                .build();
        Page<FriendApply> response = service.page(query);
        return ResponseModel.ok(response);
    }


    @Operation(summary = "查询单个好友申请")
    @GetMapping("/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_SINGLE)
    public ResponseModel<FriendApply> get(
            @PathVariable("id") Long id
    ) {
        return ResponseModel.ok(service.findById(id));
    }

}
