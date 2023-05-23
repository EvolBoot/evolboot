package org.evolboot.im.remote.conversation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.annotation.AdminClient;
import org.evolboot.core.annotation.OperationLog;
import org.evolboot.core.data.Page;
import org.evolboot.core.remote.DomainId;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.im.domain.conversation.entity.Conversation;
import org.evolboot.im.domain.conversation.ConversationAppService;
import org.evolboot.im.domain.conversation.service.ConversationQuery;
import org.evolboot.im.domain.shared.ConversationType;
import org.evolboot.im.remote.conversation.dto.ConversationCreateRequest;
import org.evolboot.im.remote.conversation.dto.ConversationUpdateRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

import static org.evolboot.im.ImAccessAuthorities.Conversation.HAS_SINGLE;
import static org.evolboot.security.api.access.AccessAuthorities.HAS_ROLE_ADMIN;
import static org.evolboot.security.api.access.AccessAuthorities.or;

/**
 * 会话
 *
 * @author evol
 * @date 2023-05-02 21:43:03
 */
@Slf4j
@RestController
@RequestMapping("/v1/admin/im/conversation")
@Tag(name = "会话", description = "会话")
@AdminClient
public class AdminConversationResourceV1 {


    private final ConversationAppService service;

    public AdminConversationResourceV1(ConversationAppService service) {
        this.service = service;
    }


    @Operation(summary = "创建会话")
    @OperationLog("创建会话")
    @PostMapping("")
//    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_CREATE)
    public ResponseModel<?> create(
            @RequestBody @Valid
            ConversationCreateRequest request
    ) {
        Conversation conversation = service.create(request);
        return ResponseModel.ok(new DomainId(conversation.id()));
    }


    @Operation(summary = "删除会话")
    @OperationLog("删除会话")
    @DeleteMapping("/{id}")
//    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_DELETE)
    public ResponseModel<?> delete(
            @PathVariable("id") Long id
    ) {
        service.delete(id);
        return ResponseModel.ok();
    }


    @Operation(summary = "修改会话")
    @OperationLog("修改会话")
    @PutMapping("/{id}")
//    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_UPDATE)
    public ResponseModel<?> update(
            @PathVariable("id") Long id,
            @RequestBody @Valid
            ConversationUpdateRequest request
    ) {
        service.update(id, request);
        return ResponseModel.ok();
    }

    @Operation(summary = "查询会话")
    @GetMapping("")
//    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_PAGE)
    public ResponseModel<Page<Conversation>> page(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "limit", defaultValue = "20") Integer limit,
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) Date startDate,
            @RequestParam(required = false) Date endDate,
            @RequestParam(required = false) ConversationType conversationType
    ) {
        ConversationQuery query = ConversationQuery
                .builder()
                .id(id)
                .startDate(startDate)
                .endDate(endDate)
                .page(page)
                .limit(limit)
                .type(conversationType)
                .build();
        Page<Conversation> response = service.page(query);
        return ResponseModel.ok(response);
    }


    @Operation(summary = "查询单个会话")
    @GetMapping("/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_SINGLE)
    public ResponseModel<Conversation> get(
            @PathVariable("id") Long id
    ) {
        return ResponseModel.ok(service.findById(id));
    }

}
