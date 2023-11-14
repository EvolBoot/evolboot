package org.evolboot.im.remote.chatrecord;

import org.evolboot.core.annotation.AdminClient;
import org.evolboot.core.annotation.OperationLog;
import org.evolboot.core.remote.DomainId;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.im.domain.chatrecord.ChatRecordAppService;
import org.evolboot.im.domain.chatrecord.ChatRecordQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.evolboot.im.domain.chatrecord.entity.ChatRecord;
import org.evolboot.im.domain.chatrecord.service.ChatRecordQuery;
import org.evolboot.im.remote.chatrecord.dto.*;
import org.evolboot.core.data.Page;

import jakarta.validation.Valid;

import static org.evolboot.security.api.access.AccessAuthorities.*;
import static org.evolboot.im.ImAccessAuthorities.ChatRecord.*;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * 聊天记录
 *
 * @author evol
 * @date 2023-06-14 18:14:00
 */
@Slf4j
@RestController
@RequestMapping("/v1/admin/im/chat-record")
@Tag(name = "聊天记录", description = "聊天记录")
@AdminClient
public class AdminChatRecordResourceV1 {

    private final ChatRecordAppService appService;
    private final ChatRecordQueryService queryService;

    public AdminChatRecordResourceV1(ChatRecordAppService appService, ChatRecordQueryService queryService) {
        this.appService = appService;
        this.queryService = queryService;
    }


    @Operation(summary = "创建聊天记录")
    @OperationLog("创建聊天记录")
    @PostMapping("")
    @PreAuthorize(HAS_ROLE_ADMIN + OR + HAS_CREATE)
    public ResponseModel<?> create(
            @RequestBody @Valid
            ChatRecordCreateRequest request
    ) {
        ChatRecord chatRecord = appService.create(request);
        return ResponseModel.ok(new DomainId(chatRecord.id()));
    }


    @Operation(summary = "删除聊天记录")
    @OperationLog("删除聊天记录")
    @DeleteMapping("/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN + OR + HAS_DELETE)
    public ResponseModel<?> delete(
            @PathVariable("id") Long id
    ) {
        appService.delete(id);
        return ResponseModel.ok();
    }


    @Operation(summary = "修改聊天记录")
    @OperationLog("修改聊天记录")
    @PutMapping
    @PreAuthorize(HAS_ROLE_ADMIN + OR + HAS_UPDATE)
    public ResponseModel<?> update(
            @RequestBody @Valid
            ChatRecordUpdateRequest request
    ) {
        appService.update(request);
        return ResponseModel.ok();
    }

    @Operation(summary = "查询聊天记录")
    @GetMapping("")
    @PreAuthorize(HAS_ROLE_ADMIN + OR + HAS_PAGE)
    public ResponseModel<Page<ChatRecord>> page(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "limit", defaultValue = "20") Integer limit,
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) Date startDate,
            @RequestParam(required = false) Date endDate
    ) {
        ChatRecordQuery query = ChatRecordQuery
                .builder()
                .id(id)
                .startDate(startDate)
                .endDate(endDate)
                .page(page)
                .limit(limit)
                .build();
        Page<ChatRecord> response = queryService.page(query);
        return ResponseModel.ok(response);
    }


    @Operation(summary = "查询单个聊天记录")
    @GetMapping("/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN + OR + HAS_SINGLE)
    public ResponseModel<ChatRecord> get(
            @PathVariable("id") Long id
    ) {
        return ResponseModel.ok(queryService.findById(id));
    }

}
