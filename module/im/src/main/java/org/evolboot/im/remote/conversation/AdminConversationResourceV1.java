package org.evolboot.im.remote.conversation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.annotation.AdminClient;
import org.evolboot.core.annotation.OperationLog;
import org.evolboot.core.data.Page;
import org.evolboot.core.remote.DomainId;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.im.domain.conversation.ConversationQueryService;
import org.evolboot.im.domain.conversation.entity.Conversation;
import org.evolboot.im.domain.conversation.ConversationAppService;
import org.evolboot.im.domain.conversation.dto.ConversationQueryRequest;
import org.evolboot.im.domain.shared.ConversationType;
import org.evolboot.im.remote.conversation.dto.ConversationCreateRequest;
import org.evolboot.im.remote.conversation.dto.ConversationUpdateRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.Date;

import static org.evolboot.im.ImAccessAuthorities.Conversation.HAS_SINGLE;
import static org.evolboot.security.api.access.AccessAuthorities.HAS_ROLE_ADMIN;
import static org.evolboot.security.api.access.AccessAuthorities.OR;

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
    private final ConversationQueryService queryService;

    public AdminConversationResourceV1(ConversationAppService service, ConversationQueryService queryService) {
        this.service = service;
        this.queryService = queryService;
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
    @PutMapping
//    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_UPDATE)
    public ResponseModel<?> update(
            @RequestBody @Valid
            ConversationUpdateRequest request
    ) {
        service.update(request);
        return ResponseModel.ok();
    }

    @Operation(summary = "查询会话")
    @GetMapping("")
//    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_PAGE)
    public ResponseModel<Page<Conversation>> page(
            @Parameter(description = "页数")
            @RequestParam(name = "page", defaultValue = "1") Integer page,

            @Parameter(description = "每页数量")
            @RequestParam(name = "limit", defaultValue = "20") Integer limit,

            @Parameter(description = "会话ID")
            @RequestParam(required = false) Long id,

            @Parameter(description = "起始时间")
            @RequestParam(required = false) Date startDate,

            @Parameter(description = "结束时间")
            @RequestParam(required = false) Date endDate,

            @Parameter(description = "搜索关键字")
            @RequestParam(required = false) String keyword,

            @RequestParam(required = false) ConversationType conversationType
    ) {
        ConversationQueryRequest query = ConversationQueryRequest
                .builder()
                .id(id)
                .startDate(startDate)
                .endDate(endDate)
                .page(page)
                .limit(limit)
                .type(conversationType)
                .build();
        Page<Conversation> response = queryService.page(query);
        return ResponseModel.ok(response);
    }


    @Operation(summary = "查询单个会话")
    @GetMapping("/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN + OR + HAS_SINGLE)
    public ResponseModel<Conversation> get(
            @PathVariable("id") Long id
    ) {
        return ResponseModel.ok(queryService.findById(id));
    }

}
