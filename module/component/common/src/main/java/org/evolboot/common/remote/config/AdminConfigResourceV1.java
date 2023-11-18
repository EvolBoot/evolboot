package org.evolboot.common.remote.config;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.evolboot.common.CommonAccessAuthorities;
import org.evolboot.common.domain.config.ConfigAppService;
import org.evolboot.common.domain.config.model.about.AboutConfig;
import org.evolboot.common.domain.config.model.sms.SmsConfig;
import org.evolboot.common.domain.config.model.system.SystemConfig;
import org.evolboot.core.annotation.AdminClient;
import org.evolboot.core.annotation.OperationLog;
import org.evolboot.core.remote.ResponseModel;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.evolboot.security.api.access.AccessAuthorities.HAS_ROLE_ADMIN;
import static org.evolboot.security.api.access.AccessAuthorities.OR;

/**
 * @author evol
 */

@RestController
@RequestMapping("/v1/admin")
@Tag(name = "全局配置", description = "全局配置")
@AdminClient
public class AdminConfigResourceV1 {

    private final ConfigAppService service;

    public AdminConfigResourceV1(ConfigAppService service) {
        this.service = service;
    }

    @Operation(summary = "修改系统配置")
    @OperationLog("修改系统配置")
    @PutMapping("/config/system")
    @PreAuthorize(HAS_ROLE_ADMIN + OR + CommonAccessAuthorities.Config.HAS_UPDATE)
    public ResponseModel<SystemConfig> setSystem(
            @RequestBody @Valid
            SystemConfig request
    ) {
        SystemConfig config = (SystemConfig) service.setPropertyValue(request);
        return ResponseModel.ok(config);
    }


    @Operation(summary = "获取系统配置")
    @OperationLog("获取系统配置")
    @GetMapping("/config/system")
    @PreAuthorize(HAS_ROLE_ADMIN + OR + CommonAccessAuthorities.Config.HAS_SINGLE)
    public ResponseModel<SystemConfig> getSystem() {
        SystemConfig config = service.getByKey(SystemConfig.KEY, SystemConfig.class);
        return ResponseModel.ok(config);
    }


    @Operation(summary = "修改关于配置")
    @OperationLog("修改关于配置")
    @PutMapping("/config/about")
    @PreAuthorize(HAS_ROLE_ADMIN + OR + CommonAccessAuthorities.Config.HAS_UPDATE)
    public ResponseModel<AboutConfig> setAbout(
            @RequestBody @Valid
            AboutConfig request
    ) {
        AboutConfig config = (AboutConfig) service.setPropertyValue(request);
        return ResponseModel.ok(config);
    }


    @Operation(summary = "获取关于配置")
    @OperationLog("获取关于配置")
    @GetMapping("/config/about")
    @PreAuthorize(HAS_ROLE_ADMIN + OR + CommonAccessAuthorities.Config.HAS_SINGLE)
    public ResponseModel<AboutConfig> getAbout() {
        AboutConfig config = service.getByKey(AboutConfig.KEY, AboutConfig.class);
        return ResponseModel.ok(config);
    }

    @Operation(summary = "修改短信配置")
    @OperationLog("修改短信配置")
    @PutMapping("/config/sms")
    @PreAuthorize(HAS_ROLE_ADMIN + OR + CommonAccessAuthorities.Config.HAS_UPDATE)
    public ResponseModel<SmsConfig> setSms(
            @RequestBody @Valid
            SmsConfig request
    ) {
        SmsConfig config = (SmsConfig) service.setPropertyValue(request);
        return ResponseModel.ok(config);
    }


    @Operation(summary = "获取短信配置")
    @OperationLog("获取短信配置")
    @GetMapping("/config/sms")
    @PreAuthorize(HAS_ROLE_ADMIN + OR + CommonAccessAuthorities.Config.HAS_SINGLE)
    public ResponseModel<SmsConfig> getSms() {
        SmsConfig config = service.getByKey(SmsConfig.KEY, SmsConfig.class);
        return ResponseModel.ok(config);
    }
}
