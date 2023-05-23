package org.evolboot.im.remote.chatrecord;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.annotation.AdminClient;
import org.evolboot.core.annotation.OperationLog;
import org.evolboot.core.data.Page;
import org.evolboot.core.remote.DomainId;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.im.domain.chatrecord.entity.ChatRecord;
import org.evolboot.im.domain.chatrecord.ChatRecordAppService;
import org.evolboot.im.domain.chatrecord.service.ChatRecordQuery;
import org.evolboot.im.remote.chatrecord.dto.ChatRecordCreateRequest;
import org.evolboot.im.remote.chatrecord.dto.ChatRecordUpdateRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

import static org.evolboot.im.ImAccessAuthorities.ChatRecord.*;
import static org.evolboot.security.api.access.AccessAuthorities.HAS_ROLE_ADMIN;
import static org.evolboot.security.api.access.AccessAuthorities.or;

/**
 * 聊天记录
 *
 * @author evol
 * @date 2023-05-03 00:02:35
 */
@Slf4j
@RestController
@RequestMapping("/v1/admin/im/chat-record")
@Tag(name = "聊天记录", description = "聊天记录")
@AdminClient
public class AdminChatRecordResourceV1 {

    private final ChatRecordAppService service;

    public AdminChatRecordResourceV1(ChatRecordAppService service) {
        this.service = service;
    }


    @Operation(summary = "创建聊天记录")
    @OperationLog("创建聊天记录")
    @PostMapping("")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_CREATE)
    public ResponseModel<?> create(
            @RequestBody @Valid
            ChatRecordCreateRequest request
    ) {
        ChatRecord chatRecord = service.create(request);
        return ResponseModel.ok(new DomainId(chatRecord.id()));
    }


    @Operation(summary = "删除聊天记录")
    @OperationLog("删除聊天记录")
    @DeleteMapping("/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_DELETE)
    public ResponseModel<?> delete(
            @PathVariable("id") Long id
    ) {
        service.delete(id);
        return ResponseModel.ok();
    }


    @Operation(summary = "修改聊天记录")
    @OperationLog("修改聊天记录")
    @PutMapping("/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_UPDATE)
    public ResponseModel<?> update(
            @PathVariable("id") Long id,
            @RequestBody @Valid
            ChatRecordUpdateRequest request
    ) {
        service.update(id, request);
        return ResponseModel.ok();
    }

    @Operation(summary = "查询聊天记录")
    @GetMapping("")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_PAGE)
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
        Page<ChatRecord> response = service.page(query);
        return ResponseModel.ok(response);
    }


    @Operation(summary = "查询单个聊天记录")
    @GetMapping("/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_SINGLE)
    public ResponseModel<ChatRecord> get(
            @PathVariable("id") Long id
    ) {
        return ResponseModel.ok(service.findById(id));
    }

}
