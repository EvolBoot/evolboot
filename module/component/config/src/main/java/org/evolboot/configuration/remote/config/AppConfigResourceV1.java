package org.evolboot.configuration.remote.config;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.evolboot.configuration.domain.config.ConfigAppService;
import org.evolboot.configuration.domain.config.system.SystemConfig;
import org.evolboot.core.annotation.ApiClient;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.security.api.annotation.Authenticated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author evol
 */

@RestController
@RequestMapping("/v1/api")
@Tag(name = "全局配置", description = "全局配置")
@ApiClient
public class AppConfigResourceV1 {

    private final ConfigAppService service;

    public AppConfigResourceV1(ConfigAppService service) {
        this.service = service;
    }


    @Operation(summary = "获取系统配置")
    @GetMapping("/configurations/system")
    @Authenticated
    public ResponseModel<SystemConfig> getSystem() {
        SystemConfig config = service.getByKey(SystemConfig.KEY, SystemConfig.class);
        return ResponseModel.ok(config);
    }


}
