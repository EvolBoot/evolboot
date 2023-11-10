package org.evolboot.system.remote.bff;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.annotation.AdminClient;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.system.domain.bff.SystemBffAppService;
import org.evolboot.system.domain.bff.dto.BffDict;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.evolboot.security.api.access.AccessAuthorities.HAS_ROLE_ADMIN;


/**
 * @author evol
 */
@Slf4j
@RestController
@RequestMapping("/v1/admin/system/bff")
@Tag(name = "SystemBFF", description = "SystemBFF")
@AdminClient
public class AdminSystemBffResourceV1 {

    private final SystemBffAppService service;

    public AdminSystemBffResourceV1(SystemBffAppService service) {
        this.service = service;
    }

    /**
     * @return
     */
    @GetMapping("/dict/all")
    @Operation(summary = "字典数据(所有)")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ResponseModel<List<BffDict>> findBffDict(
    ) {
        List<BffDict> bffDict = service.findBffDict();
        return ResponseModel.ok(bffDict);
    }




}
