package org.evolboot.im.remote.userconversation;

import org.evolboot.core.annotation.AdminClient;
import org.evolboot.core.annotation.OperationLog;
import org.evolboot.core.remote.DomainId;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.im.domain.userconversation.UserConversation;
import org.evolboot.im.domain.userconversation.UserConversationAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.evolboot.im.domain.userconversation.UserConversationQuery;
import org.evolboot.core.data.Page;

import javax.validation.Valid;
import java.util.List;

import static org.evolboot.security.api.access.AccessAuthorities.*;
import static org.evolboot.im.ImAccessAuthorities.UserConversation.*;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;

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

    public AdminUserConversationResourceV1(UserConversationAppService service) {
        this.service = service;
    }


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
    @PutMapping("/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_UPDATE)
    public ResponseModel<?> update(
            @PathVariable("id") Long id,
            @RequestBody @Valid
                    UserConversationUpdateRequest request
    ) {
        service.update(id, request);
        return ResponseModel.ok();
    }

    @Operation(summary = "查询用户会话")
    @GetMapping("")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_PAGE)
    public ResponseModel<Page<UserConversation>> page(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "limit", defaultValue = "20") Integer limit,
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) Date startDate,
            @RequestParam(required = false) Date endDate
    ) {
        UserConversationQuery query = UserConversationQuery
                .builder()
                .id(id)
                .startDate(startDate)
                .endDate(endDate)
                .page(page)
                .limit(limit)
                .build();
        Page<UserConversation> response = service.page(query);
        return ResponseModel.ok(response);
    }


    @Operation(summary = "查询单个用户会话")
    @GetMapping("/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_SINGLE)
    public ResponseModel<UserConversation> get(
            @PathVariable("id") Long id
    ) {
        return ResponseModel.ok(service.findById(id));
    }

}