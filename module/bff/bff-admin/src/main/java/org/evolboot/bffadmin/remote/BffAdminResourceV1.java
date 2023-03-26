package org.evolboot.bffadmin.remote;

import org.evolboot.bffadmin.domain.BffAdminQuery;
import org.evolboot.bffadmin.domain.BffAdminService;
import org.evolboot.bffadmin.domain.response.BffStaffUser;
import org.evolboot.bffadmin.domain.response.BffUser;
import org.evolboot.core.annotation.AdminClient;
import org.evolboot.core.data.Page;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.identity.domain.permission.Permission;
import org.evolboot.security.api.SecurityAccessTokenHolder;
import org.evolboot.security.api.annotation.Authenticated;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

import static org.evolboot.security.api.access.AccessAuthorities.HAS_ROLE_ADMIN;

/**
 * @author evol
 *
 */
@Slf4j
@RestController
@RequestMapping("/v1/admin/bff")
@Tag(name = "BFF", description = "BFF")
@AdminClient
public class BffAdminResourceV1 {

    private final BffAdminService service;

    public BffAdminResourceV1(BffAdminService service) {
        this.service = service;
    }

    @Operation(summary = "查找用户")
    @GetMapping("/user")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ResponseModel<Page<BffUser>> findUser(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "limit", defaultValue = "20") Integer limit

    ) {
        Page<BffUser> bffUsers = service.findUser(BffAdminQuery.builder()
                .page(page)
                .limit(limit)
                .build());
        return ResponseModel.ok((bffUsers));
    }

    /**
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/user-staff")
    @Operation(summary = "员工列表(带角色信息)")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ResponseModel<Page<BffStaffUser>> findStaffUser(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "limit", defaultValue = "10") Integer limit
    ) {
        BffAdminQuery query = BffAdminQuery.builder()
                .page(page)
                .limit(limit)
                .build();
        return ResponseModel.ok(service.findStaffUser(query));
    }


    @Operation(summary = "当前服务器时间")
    @GetMapping("/current-server-date")
    public ResponseModel<Date> currentServerDate(
    ) {
        return ResponseModel.ok((new Date()));
    }

    /**
     * @return
     */
    @GetMapping("/download-auth-file")
    @Operation(summary = "下载权限表格")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ResponseModel<String> download() {
        String s = service.downloadAuthorities();
        return ResponseModel.ok(s);
    }

    /**
     * @return
     */
    @GetMapping("/current-user-permission")
    @Operation(summary = "当前登录用户权限(树形)")
    @Authenticated
    public ResponseModel<List<Permission>> currentStaffPermission() {
        return ResponseModel.ok(service.findPermissionByUserIdConvertTree(SecurityAccessTokenHolder.getPrincipalId()));
    }


}
