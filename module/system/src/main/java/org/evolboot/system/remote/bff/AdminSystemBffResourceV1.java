package org.evolboot.system.remote.bff;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.annotation.AdminClient;
import org.evolboot.system.domain.bff.SystemBffAppService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author evol
 */
@Slf4j
@RestController
@RequestMapping("/admin/v1/system/bff")
@Tag(name = "SystemBFF", description = "SystemBFF")
@AdminClient
public class AdminSystemBffResourceV1 {

    private final SystemBffAppService service;

    public AdminSystemBffResourceV1(SystemBffAppService service) {
        this.service = service;
    }



}
