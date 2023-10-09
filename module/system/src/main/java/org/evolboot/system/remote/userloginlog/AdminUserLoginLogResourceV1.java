package org.evolboot.system.remote.userloginlog;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.evolboot.core.annotation.AdminClient;
import org.evolboot.core.data.Page;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.system.domain.userloginlog.entity.UserLoginLog;
import org.evolboot.system.domain.userloginlog.UserLoginLogAppService;
import org.evolboot.system.domain.userloginlog.service.UserLoginLogQuery;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.evolboot.security.api.access.AccessAuthorities.HAS_ROLE_ADMIN;
import static org.evolboot.security.api.access.AccessAuthorities.OR;
import static org.evolboot.system.SystemAccessAuthorities.UserLoginLog.HAS_PAGE;

/**
 * @author evol
 */
@RestController
@RequestMapping("/v1/admin/system/user-login-log")
@Tag(name = "用户登录日志", description = "用户登录日志")
@AdminClient
public class AdminUserLoginLogResourceV1 {

    private final UserLoginLogAppService service;

    public AdminUserLoginLogResourceV1(UserLoginLogAppService service) {
        this.service = service;
    }


    @Operation(summary = "查询用户登录日志")
    @GetMapping
    @PreAuthorize(HAS_ROLE_ADMIN + OR + HAS_PAGE)
    public ResponseModel<Page<UserLoginLog>> page(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "limit", defaultValue = "20") Integer limit,
            @RequestParam(required = false) String loginIp,
            @RequestParam(required = false) Long userId
    ) {
        UserLoginLogQuery query = UserLoginLogQuery.builder()
                .page(page)
                .limit(limit)
                .userId(userId)
                .loginIp(loginIp)
                .build();
        return ResponseModel.ok(service.page(query));
    }

}
