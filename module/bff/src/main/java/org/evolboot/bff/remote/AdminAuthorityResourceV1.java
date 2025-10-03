package org.evolboot.bff.remote;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.bff.domain.admin.BffDownloadAuthoritiesService;
import org.evolboot.bff.domain.admin.dto.AuthorityOption;
import org.evolboot.bff.domain.admin.dto.AuthorityTree;
import org.evolboot.core.annotation.AdminClient;
import org.evolboot.core.remote.ResponseModel;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.evolboot.security.api.access.AccessAuthorities.HAS_ROLE_ADMIN;

/**
 * 权限管理接口
 * @author evol
 */
@Slf4j
@RestController
@RequestMapping("/v1/admin/bff/authorities")
@Tag(name = "权限管理", description = "权限管理")
@AdminClient
public class AdminAuthorityResourceV1 {

    private final BffDownloadAuthoritiesService authoritiesService;

    public AdminAuthorityResourceV1(BffDownloadAuthoritiesService authoritiesService) {
        this.authoritiesService = authoritiesService;
    }

    @Operation(summary = "获取所有可用权限列表")
    @GetMapping("/available")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ResponseModel<List<AuthorityOption>> getAvailable() {
        return ResponseModel.ok(authoritiesService.getAvailableAuthorities());
    }

    @Operation(summary = "获取权限树")
    @GetMapping("/tree")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ResponseModel<List<AuthorityTree>> getTree() {
        return ResponseModel.ok(authoritiesService.getAuthoritiesTree());
    }

    @Operation(summary = "搜索权限")
    @GetMapping("/search")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ResponseModel<List<AuthorityOption>> search(
            @RequestParam String keyword
    ) {
        return ResponseModel.ok(authoritiesService.searchAuthorities(keyword));
    }

    @Operation(summary = "下载权限Excel")
    @GetMapping("/download")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ResponseModel<String> download() {
        return ResponseModel.ok(authoritiesService.download());
    }
}
