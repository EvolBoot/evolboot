package org.evolboot.system.remote.dictkey;

import org.evolboot.core.annotation.ApiClient;
import org.evolboot.core.annotation.OperationLog;
import org.evolboot.core.remote.DomainId;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.system.domain.dictkey.DictKey;
import org.evolboot.system.domain.dictkey.DictKeyAppService;
import org.evolboot.system.domain.dictkey.DefaultDictKeyAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.evolboot.core.data.Page;
import org.evolboot.system.domain.dictkey.DictKeyQuery;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * 字典key
 *
 * @author evol
 * @date 2023-05-07 12:29:33
 */
@Slf4j
@RestController
@RequestMapping("/v1/api/system/dict-key")
@Tag(name = "字典key", description = "字典key")
@ApiClient
public class AppDictKeyResourceV1 {

    private final DictKeyAppService service;

    public AppDictKeyResourceV1(DictKeyAppService service) {
        this.service = service;
    }


    /*

    @Operation(summary = "查询字典key")
    @GetMapping("")
    public ResponseModel<List<DictKeyLocaleResponse>> findAll(
    ) {
        List<DictKey> result = service.findAll();
        return ResponseModel.ok(DictKeyLocaleResponse.of(result));
    }

    @Operation(summary = "查询字典key")
    @GetMapping("")
    public ResponseModel<Page<DictKeyLocaleResponse>> page(
       @RequestParam(name = "page", defaultValue = "1") Integer page,
       @RequestParam(name = "limit", defaultValue = "20") Integer limit
    ) {
       DictKeyQuery query = DictKeyQuery
                .builder()
                .page(page)
                .limit(limit)
                .build();
        Page<DictKey> result = service.page(query);
        return ResponseModel.ok(DictKeyLocaleResponse.of(result));
    }

    */

}
