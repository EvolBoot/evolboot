package org.evolboot.identity.remote.bff;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.annotation.AdminClient;
import org.evolboot.core.data.Page;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.identity.domain.bff.IdentityBffAppService;
import org.evolboot.identity.domain.bff.dto.BffStaffUser;
import org.evolboot.identity.domain.bff.dto.IdentityBffQueryRequest;
import org.evolboot.identity.domain.permission.entity.Permission;
import org.evolboot.security.api.SecurityAccessTokenHolder;
import org.evolboot.security.api.annotation.Authenticated;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.evolboot.security.api.access.AccessAuthorities.HAS_ROLE_SUPER_ADMIN;


/**
 * @author evol
 */
@Slf4j
@RestController
@RequestMapping("/admin/v1/identity/bff")
@Tag(name = "IdentityBff", description = "IdentityBff")
@AdminClient
public class AdminIdentityBffResourceV1 {

    private final IdentityBffAppService service;

    public AdminIdentityBffResourceV1(IdentityBffAppService service) {
        this.service = service;
    }

    /**
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/user-staff")
    @Operation(summary = "员工列表(带角色信息)")
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN)
    public ResponseModel<Page<BffStaffUser>> findStaffUser(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "limit", defaultValue = "10") Integer limit
    ) {
        IdentityBffQueryRequest query = IdentityBffQueryRequest.builder()
                .page(page)
                .limit(limit)
                .build();
        return ResponseModel.ok(service.findStaffUser(query));
    }


    /**
     * @return
     */
    @GetMapping("/download-auth-file")
    @Operation(summary = "下载权限表格")
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN)
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
        return ResponseModel.ok(service.findPermissionByUserIdAndTenantIdConvertTree(SecurityAccessTokenHolder.getUserId(), null));
    }


}
