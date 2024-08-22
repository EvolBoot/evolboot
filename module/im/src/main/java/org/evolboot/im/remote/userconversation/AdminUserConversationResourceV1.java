package org.evolboot.im.remote.userconversation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.annotation.AdminClient;
import org.evolboot.core.data.Page;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.im.domain.userconversation.UserConversationQueryService;
import org.evolboot.im.domain.userconversation.entity.UserConversation;
import org.evolboot.im.domain.userconversation.UserConversationAppService;
import org.evolboot.im.domain.userconversation.dto.UserConversationQuery;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static org.evolboot.im.ImAccessAuthorities.UserConversation.HAS_PAGE;
import static org.evolboot.im.ImAccessAuthorities.UserConversation.HAS_SINGLE;
import static org.evolboot.security.api.access.AccessAuthorities.HAS_ROLE_ADMIN;
import static org.evolboot.security.api.access.AccessAuthorities.OR;

/**
 * 用户会话
 *
 * @author evol
 * @date 2023-05-02 23:36:54
 */
@Slf4j
@RestController
@RequestMapping("/v1/admin/im/user-conversation")
@Tag(name = "用户会话", description = "用户会话")
@AdminClient
public class AdminUserConversationResourceV1 {

    private final UserConversationAppService service;
    private final UserConversationQueryService queryService;

    public AdminUserConversationResourceV1(UserConversationAppService service, UserConversationQueryService queryService) {
        this.service = service;
        this.queryService = queryService;
    }

/*

    @Operation(summary = "创建用户会话")
    @OperationLog("创建用户会话")
    @PostMapping("")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_CREATE)
    public ResponseModel<?> create(
            @RequestBody @Valid
                    UserConversationCreateRequest request
    ) {
        UserConversation userConversation = service.create(request);
        return ResponseModel.ok(new DomainId(userConversation.id()));
    }


    @Operation(summary = "删除用户会话")
    @OperationLog("删除用户会话")
    @DeleteMapping("/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_DELETE)
    public ResponseModel<?> delete(
            @PathVariable("id") Long id
    ) {
        service.delete(id);
        return ResponseModel.ok();
    }


    @Operation(summary = "修改用户会话")
    @OperationLog("修改用户会话")
    @PutMapping
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_UPDATE)
    public ResponseModel<?> update(
            @RequestBody @Valid
                    UserConversationUpdateRequest request
    ) {
        service.update(request);
        return ResponseModel.ok();
    }
*/

    @Operation(summary = "查询用户会话")
    @GetMapping
    @PreAuthorize(HAS_ROLE_ADMIN + OR + HAS_PAGE)
    public ResponseModel<Page<UserConversation>> page(
            @Parameter(description = "页数")
            @RequestParam(name = "page", defaultValue = "1") Integer page,

            @Parameter(description = "每页数量")
            @RequestParam(name = "limit", defaultValue = "20") Integer limit,

            @Parameter(description = "用户会话ID")
            @RequestParam(required = false) Long id,

            @Parameter(description = "起始时间")
            @RequestParam(required = false) Date beginAt,

            @Parameter(description = "结束时间")
            @RequestParam(required = false) Date endAt,

            @Parameter(description = "搜索关键字")
            @RequestParam(required = false) String keyword
    ) {
        UserConversationQuery query = UserConversationQuery
                .builder()
                .id(id)
                .beginAt(beginAt)
                .endAt(endAt)
                .page(page)
                .limit(limit)
                .build();
        Page<UserConversation> response = queryService.page(query);
        return ResponseModel.ok(response);
    }


    @Operation(summary = "查询单个用户会话")
    @GetMapping("/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN + OR + HAS_SINGLE)
    public ResponseModel<UserConversation> get(
            @PathVariable("id") Long id
    ) {
        return ResponseModel.ok(queryService.findById(id));
    }

}
