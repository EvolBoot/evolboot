package org.evolboot.system.remote.operationlog;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.evolboot.core.annotation.ApiClient;
import org.evolboot.system.domain.operationlog.OperationLogAppService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author evol
 */
@RestController
@RequestMapping("/api/v1")
@Tag(name = "OperationLog", description = "操作日志管理")
@ApiClient
public class AppOperationLogResourceV1 {

    private final OperationLogAppService service;

    public AppOperationLogResourceV1(OperationLogAppService service) {
        this.service = service;
    }


}
