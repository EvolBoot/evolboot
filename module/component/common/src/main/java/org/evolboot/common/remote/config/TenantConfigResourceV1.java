package org.evolboot.common.remote.config;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.evolboot.common.CommonAccessAuthorities;
import org.evolboot.common.domain.config.ConfigAppService;
import org.evolboot.common.domain.config.model.system.SystemConfig;
import org.evolboot.core.annotation.AdminClient;
import org.evolboot.core.annotation.OperationLog;
import org.evolboot.core.remote.ResponseModel;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.evolboot.security.api.access.AccessAuthorities.HAS_ROLE_TENANT_OWNER;
import static org.evolboot.security.api.access.AccessAuthorities.OR;

/**
 * Tenant Config Resource
 * 租户配置管理 - 租户所有者和租户员工可访问
 * 管理租户自定义配置
 */
@RestController
@RequestMapping("/v1/tenant")
@Tag(name = "租户配置", description = "租户配置")
@AdminClient
public class TenantConfigResourceV1 {

    private final ConfigAppService service;

    public TenantConfigResourceV1(ConfigAppService service) {
        this.service = service;
    }

    /**
     * 修改租户系统配置
     */
    @Operation(summary = "修改租户系统配置")
    @OperationLog("修改租户系统配置")
    @PutMapping("/config/system")
    @PreAuthorize(HAS_ROLE_TENANT_OWNER + OR + CommonAccessAuthorities.Config.HAS_UPDATE)
    public ResponseModel<SystemConfig> setSystem(
            @RequestBody @Valid SystemConfig request
    ) {
        SystemConfig config = (SystemConfig) service.setPropertyValue(request);
        return ResponseModel.ok(config);
    }

    /**
     * 获取租户系统配置
     */
    @Operation(summary = "获取租户系统配置")
    @GetMapping("/config/system")
    @PreAuthorize(HAS_ROLE_TENANT_OWNER + OR + CommonAccessAuthorities.Config.HAS_SINGLE)
    public ResponseModel<SystemConfig> getSystem() {
        SystemConfig config = service.getByKey(SystemConfig.KEY, SystemConfig.class);
        return ResponseModel.ok(config);
    }

}
