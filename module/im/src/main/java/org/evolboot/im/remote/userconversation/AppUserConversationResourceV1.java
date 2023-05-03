package org.evolboot.im.remote.userconversation;

import org.evolboot.core.annotation.ApiClient;
import org.evolboot.core.annotation.OperationLog;
import org.evolboot.core.remote.DomainId;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.im.domain.userconversation.UserConversation;
import org.evolboot.im.domain.userconversation.UserConversationAppService;
import org.evolboot.im.domain.userconversation.DefaultUserConversationAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.evolboot.core.data.Page;
import org.evolboot.im.domain.userconversation.UserConversationQuery;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * 用户会话
 *
 * @author evol
 * @date 2023-05-02 23:36:54
 */
@Slf4j
@RestController
@RequestMapping("/v1/api/im/user-conversation")
@Tag(name = "用户会话", description = "用户会话")
@ApiClient
public class AppUserConversationResourceV1 {

    private final UserConversationAppService service;

    public AppUserConversationResourceV1(UserConversationAppService service) {
        this.service = service;
    }


    /*

    @Operation(summary = "查询用户会话")
    @GetMapping("")
    public ResponseModel<List<UserConversationLocaleResponse>> findAll(
    ) {
        List<UserConversation> result = service.findAll();
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
        Page<UserConversation> result = service.page(query);
        return ResponseModel.ok(UserConversationLocaleResponse.of(result));
    }

    */

}
