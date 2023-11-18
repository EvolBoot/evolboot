package org.evolboot.common.remote.dictkey;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.annotation.ApiClient;
import org.evolboot.common.domain.dictkey.DictKeyAppService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 字典key
 *
 * @author evol
 * @date 2023-05-07 12:29:33
 */
@Slf4j
@RestController
@RequestMapping("/v1/api/common/dict-key")
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
