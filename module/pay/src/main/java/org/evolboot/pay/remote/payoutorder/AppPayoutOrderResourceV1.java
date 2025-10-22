package org.evolboot.pay.remote.payoutorder;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.annotation.ApiClient;
import org.evolboot.pay.domain.payoutorder.PayoutOrderAppService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/payout-order")
@Tag(name = "代付订单", description = "代付订单")
@ApiClient
public class AppPayoutOrderResourceV1 {

    private final PayoutOrderAppService service;

    public AppPayoutOrderResourceV1(PayoutOrderAppService service) {
        this.service = service;
    }


    /*

    @Operation(summary = "查询代付订单")
    @GetMapping("")
    public ResponseModel<List<PayoutOrderLocaleResponse>> findAll(
    ) {
        List<PayoutOrder> result = service.findAll();
        return ResponseModel.ok(PayoutOrderLocaleResponse.of(result));
    }

    @Operation(summary = "查询代付订单")
    @GetMapping("")
    public ResponseModel<Page<PayoutOrderLocaleResponse>> page(
       @RequestParam(name = "page", defaultValue = "1") Integer page,
       @RequestParam(name = "limit", defaultValue = "20") Integer limit
    ) {
       PayoutOrderQuery query = PayoutOrderQuery
                .builder()
                .page(page)
                .limit(limit)
                .build();
        Page<PayoutOrder> result = service.page(query);
        return ResponseModel.ok(PayoutOrderLocaleResponse.of(result));
    }

    */

}
