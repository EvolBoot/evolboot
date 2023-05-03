package org.evolboot.im.remote.chatrecord;

import org.evolboot.core.annotation.ApiClient;
import org.evolboot.core.annotation.OperationLog;
import org.evolboot.core.remote.DomainId;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.im.domain.chatrecord.ChatRecord;
import org.evolboot.im.domain.chatrecord.ChatRecordAppService;
import org.evolboot.im.domain.chatrecord.DefaultChatRecordAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.evolboot.core.data.Page;
import org.evolboot.im.domain.chatrecord.ChatRecordQuery;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * 聊天记录
 *
 * @author evol
 * @date 2023-05-03 00:02:35
 */
@Slf4j
@RestController
@RequestMapping("/v1/api/im/chat-record")
@Tag(name = "聊天记录", description = "聊天记录")
@ApiClient
public class AppChatRecordResourceV1 {

    private final ChatRecordAppService service;

    public AppChatRecordResourceV1(ChatRecordAppService service) {
        this.service = service;
    }


    /*

    @Operation(summary = "查询聊天记录")
    @GetMapping("")
    public ResponseModel<List<ChatRecordLocaleResponse>> findAll(
    ) {
        List<ChatRecord> result = service.findAll();
        return ResponseModel.ok(ChatRecordLocaleResponse.of(result));
    }

    @Operation(summary = "查询聊天记录")
    @GetMapping("")
    public ResponseModel<Page<ChatRecordLocaleResponse>> page(
       @RequestParam(name = "page", defaultValue = "1") Integer page,
       @RequestParam(name = "limit", defaultValue = "20") Integer limit
    ) {
       ChatRecordQuery query = ChatRecordQuery
                .builder()
                .page(page)
                .limit(limit)
                .build();
        Page<ChatRecord> result = service.page(query);
        return ResponseModel.ok(ChatRecordLocaleResponse.of(result));
    }

    */

}
