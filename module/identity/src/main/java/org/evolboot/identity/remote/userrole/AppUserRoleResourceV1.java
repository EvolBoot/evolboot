package org.evolboot.identity.remote.userrole;

import org.evolboot.core.annotation.ApiClient;
import org.evolboot.identity.domain.userrole.UserRoleAppService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户角色
 *
 * @author evol
 * 
 */
@Slf4j
@RestController
@RequestMapping("/v1/api")
@Tag(name = "用户角色管理", description = "用户角色管理")
@ApiClient
public class AppUserRoleResourceV1 {

    private final UserRoleAppService service;

    public AppUserRoleResourceV1(UserRoleAppService service) {
        this.service = service;
    }


}
