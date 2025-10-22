package org.evolboot.bff.remote;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.Version;
import org.evolboot.bff.domain.admin.BffDownloadAuthoritiesService;
import org.evolboot.bff.domain.admin.dto.AuthorityOption;
import org.evolboot.bff.domain.admin.dto.AuthorityTree;
import org.evolboot.core.annotation.AdminClient;
import org.evolboot.core.annotation.TenantClient;
import org.evolboot.core.remote.ResponseModel;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.evolboot.bff.BffAdminAccessAuthorities.BffAdminAuthorityResource.HAS_BFF_AUTHORITY;
import static org.evolboot.security.api.access.AccessAuthorities.*;

/**
 * 权限管理接口
 *
 * @author evol
 */
@Slf4j
@RestController
@RequestMapping("/admin/v1/bff/authorities")
@Tag(name = "权限管理", description = "权限管理")
@AdminClient
public class BffAdminAuthorityResourceV1 {

    private final BffDownloadAuthoritiesService authoritiesService;

    public BffAdminAuthorityResourceV1(BffDownloadAuthoritiesService authoritiesService) {
        this.authoritiesService = authoritiesService;
    }

    @Operation(summary = "获取租户所有可用权限列表")
    @GetMapping("/tenant/available")
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN + OR + HAS_BFF_AUTHORITY)
    public ResponseModel<List<AuthorityOption>> getTenantAvailable() {
        return ResponseModel.ok(authoritiesService.getAvailableAuthorities(TenantClient.class));
    }

    @Operation(summary = "获取管理员所有可用权限列表")
    @GetMapping("/admin/available")
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN + OR + HAS_BFF_AUTHORITY)
    public ResponseModel<List<AuthorityOption>> getAdminAvailable() {
        return ResponseModel.ok(authoritiesService.getAvailableAuthorities(AdminClient.class));
    }

    @Operation(summary = "获取管理员权限树")
    @GetMapping("/admin/tree")
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN + OR + HAS_BFF_AUTHORITY)
    public ResponseModel<List<AuthorityTree>> getAdminTree() {
        return ResponseModel.ok(authoritiesService.getAuthoritiesTree(AdminClient.class));
    }

    @Operation(summary = "获取租户权限树")
    @GetMapping("/tenant/tree")
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN + OR + HAS_BFF_AUTHORITY)
    public ResponseModel<List<AuthorityTree>> getTenantTree() {
        return ResponseModel.ok(authoritiesService.getAuthoritiesTree(TenantClient.class));
    }

    @Operation(summary = "搜索权限")
    @GetMapping("/search")
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN + OR + HAS_BFF_AUTHORITY)
    public ResponseModel<List<AuthorityOption>> search(
            @RequestParam String keyword
    ) {
        return ResponseModel.ok(authoritiesService.searchAuthorities(keyword));
    }

    @Operation(summary = "下载权限Excel")
    @GetMapping("/download")
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN + OR + HAS_BFF_AUTHORITY)
    public ResponseModel<String> download() {
        return ResponseModel.ok(authoritiesService.download());
    }
    public static void main(String[] args) {
        System.out.println(Version.class.getPackageName());

    }
}
