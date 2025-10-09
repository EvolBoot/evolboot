package org.evolboot.im.remote.userconversation;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.annotation.ApiClient;
import org.evolboot.im.domain.userconversation.UserConversationAppService;
import org.evolboot.im.domain.userconversation.UserConversationQueryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户会话
 *
 * @author evol
 * @date 2023-05-02 23:36:54
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/im/user-conversation")
@Tag(name = "用户会话", description = "用户会话")
@ApiClient
public class AppUserConversationResourceV1 {

    private final UserConversationAppService service;
    private final UserConversationQueryService queryService;

    public AppUserConversationResourceV1(UserConversationAppService service, UserConversationQueryService queryService) {
        this.service = service;
        this.queryService = queryService;
    }


    /*

    @Operation(summary = "查询用户会话")
    @GetMapping("")
    public ResponseModel<List<UserConversationLocaleResponse>> findAll(
    ) {
        List<UserConversation> result = queryService.findAll();
        return ResponseModel.ok(UserConversationLocaleResponse.of(result));
    }

    @Operation(summary = "查询用户会话")
    @GetMapping("")
    public ResponseModel<Page<UserConversationLocaleResponse>> page(
       @RequestParam(name = "page", defaultValue = "1") Integer page,
       @RequestParam(name = "limit", defaultValue = "20") Integer limit
    ) {
       UserConversationQuery query = UserConversationQuery
                .builder()
                .page(page)
                .limit(limit)
                .build();
        Page<UserConversation> result = queryService.page(query);
        return ResponseModel.ok(UserConversationLocaleResponse.of(result));
    }

    */

}
