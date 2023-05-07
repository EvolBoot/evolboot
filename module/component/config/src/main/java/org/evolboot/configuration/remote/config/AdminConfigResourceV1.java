package org.evolboot.configuration.remote.config;

import org.evolboot.configuration.ConfigAccessAuthorities;
import org.evolboot.configuration.domain.config.ConfigAppService;
import org.evolboot.configuration.domain.config.about.AboutConfig;
import org.evolboot.configuration.domain.config.sms.SmsConfig;
import org.evolboot.configuration.domain.config.system.SystemConfig;
import org.evolboot.core.annotation.AdminClient;
import org.evolboot.core.annotation.OperationLog;
import org.evolboot.core.remote.ResponseModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.evolboot.security.api.access.AccessAuthorities.HAS_ROLE_ADMIN;
import static org.evolboot.security.api.access.AccessAuthorities.or;

/**
 * @author evol
 */

@RestController
@RequestMapping("/v1/admin")
@Tag(name = "Configuration", description = "全局配置管理")
@AdminClient
public class AdminConfigResourceV1 {

    private final ConfigAppService service;

    public AdminConfigResourceV1(ConfigAppService service) {
        this.service = service;
    }

    @Operation(summary = "修改系统配置")
    @OperationLog("修改系统配置")
    @PutMapping("/configurations/system")
    @PreAuthorize(HAS_ROLE_ADMIN + or + ConfigAccessAuthorities.System.HAS_UPDATE)
    public ResponseModel<SystemConfig> setSystem(
            @RequestBody @Valid
                    SystemConfig request
    ) {
        SystemConfig config = (SystemConfig) service.setPropertyValue(request);
        return ResponseModel.ok(config);
    }


    @Operation(summary = "获取系统配置")
    @OperationLog("获取系统配置")
    @GetMapping("/configurations/system")
    @PreAuthorize(HAS_ROLE_ADMIN + or + ConfigAccessAuthorities.System.HAS_SINGLE)
    public ResponseModel<SystemConfig> getSystem() {
        SystemConfig config = service.getByKey(SystemConfig.KEY, SystemConfig.class);
        return ResponseModel.ok(config);
    }


    @Operation(summary = "修改关于配置")
    @OperationLog("修改关于配置")
    @PutMapping("/configurations/about")
    @PreAuthorize(HAS_ROLE_ADMIN + or + ConfigAccessAuthorities.System.HAS_UPDATE)
    public ResponseModel<AboutConfig> setAbout(
            @RequestBody @Valid
                    AboutConfig request
    ) {
        AboutConfig config = (AboutConfig) service.setPropertyValue(request);
        return ResponseModel.ok(config);
    }


    @Operation(summary = "获取关于配置")
    @OperationLog("获取关于配置")
    @GetMapping("/configurations/about")
    @PreAuthorize(HAS_ROLE_ADMIN + or + ConfigAccessAuthorities.System.HAS_SINGLE)
    public ResponseModel<AboutConfig> getAbout() {
        AboutConfig config = service.getByKey(AboutConfig.KEY, AboutConfig.class);
        return ResponseModel.ok(config);
    }

    @Operation(summary = "修改短信配置")
    @OperationLog("修改短信配置")
    @PutMapping("/configurations/sms")
    @PreAuthorize(HAS_ROLE_ADMIN + or + ConfigAccessAuthorities.System.HAS_UPDATE)
    public ResponseModel<SmsConfig> setSms(
            @RequestBody @Valid
                    SmsConfig request
    ) {
        SmsConfig config = (SmsConfig) service.setPropertyValue(request);
        return ResponseModel.ok(config);
    }


    @Operation(summary = "获取短信配置")
    @OperationLog("获取短信配置")
    @GetMapping("/configurations/sms")
    @PreAuthorize(HAS_ROLE_ADMIN + or + ConfigAccessAuthorities.System.HAS_SINGLE)
    public ResponseModel<SmsConfig> getSms() {
        SmsConfig config = service.getByKey(SmsConfig.KEY, SmsConfig.class);
        return ResponseModel.ok(config);
    }
}
