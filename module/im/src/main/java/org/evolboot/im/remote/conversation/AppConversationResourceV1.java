package org.evolboot.im.remote.conversation;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.annotation.ApiClient;
import org.evolboot.im.domain.conversation.ConversationAppService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 会话
 *
 * @author evol
 * @date 2023-05-02 21:43:03
 */
@Slf4j
@RestController
@RequestMapping("/v1/api/im/conversation")
@Tag(name = "会话", description = "会话")
@ApiClient
public class AppConversationResourceV1 {

    private final ConversationAppService service;

    public AppConversationResourceV1(ConversationAppService service) {
        this.service = service;
    }


    /*

    @Operation(summary = "查询会话")
    @GetMapping("")
    public ResponseModel<List<ConversationLocaleResponse>> findAll(
    ) {
        List<Conversation> result = service.findAll();
        return ResponseModel.ok(ConversationLocaleResponse.of(result));
    }

    @Operation(summary = "查询会话")
    @GetMapping("")
    public ResponseModel<Page<ConversationLocaleResponse>> page(
       @RequestParam(name = "page", defaultValue = "1") Integer page,
       @RequestParam(name = "limit", defaultValue = "20") Integer limit
    ) {
       ConversationQuery query = ConversationQuery
                .builder()
                .page(page)
                .limit(limit)
                .build();
        Page<Conversation> result = service.page(query);
        return ResponseModel.ok(ConversationLocaleResponse.of(result));
    }

    */

}
