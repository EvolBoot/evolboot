package org.evolboot.system.remote.appupgrade;

import org.evolboot.core.annotation.ApiClient;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.system.domain.appupgrade.AppUpgrade;
import org.evolboot.system.domain.appupgrade.AppUpgradeAppService;
import org.evolboot.system.domain.appupgrade.ClientType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * APP更新
 *
 * @author evol
 *
 */
@Slf4j
@RestController
@RequestMapping("/v1/api/system")
@Tag(name = "APP更新", description = "APP更新")
@ApiClient
public class AppAppUpgradeResourceV1 {

    private final AppUpgradeAppService service;

    public AppAppUpgradeResourceV1(AppUpgradeAppService service) {
        this.service = service;
    }


    @Operation(summary = "查询APP更新")
    @GetMapping("/app-upgrade/check")
    public ResponseModel<AppUpgradeLocaleResponse> check(
            ClientType clientType
    ) {
        AppUpgrade appUpgrade = service.check(clientType);
        return ResponseModel.ok(AppUpgradeLocaleResponse.of(appUpgrade));
    }


}
