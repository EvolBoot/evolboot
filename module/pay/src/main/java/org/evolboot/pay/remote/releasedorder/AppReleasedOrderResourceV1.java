package org.evolboot.pay.remote.releasedorder;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.annotation.ApiClient;
import org.evolboot.pay.domain.releasedorder.ReleasedOrderAppService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 代付订单
 *
 * @author evol
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/released-order")
@Tag(name = "代付订单", description = "代付订单")
@ApiClient
public class AppReleasedOrderResourceV1 {

    private final ReleasedOrderAppService service;

    public AppReleasedOrderResourceV1(ReleasedOrderAppService service) {
        this.service = service;
    }


    /*

    @Operation(summary = "查询代付订单")
    @GetMapping("")
    public ResponseModel<List<ReleasedOrderLocaleResponse>> findAll(
    ) {
        List<ReleasedOrder> result = service.findAll();
        return ResponseModel.ok(ReleasedOrderLocaleResponse.of(result));
    }

    @Operation(summary = "查询代付订单")
    @GetMapping("")
    public ResponseModel<Page<ReleasedOrderLocaleResponse>> page(
       @RequestParam(name = "page", defaultValue = "1") Integer page,
       @RequestParam(name = "limit", defaultValue = "20") Integer limit
    ) {
       ReleasedOrderQuery query = ReleasedOrderQuery
                .builder()
                .page(page)
                .limit(limit)
                .build();
        Page<ReleasedOrder> result = service.page(query);
        return ResponseModel.ok(ReleasedOrderLocaleResponse.of(result));
    }

    */

}
