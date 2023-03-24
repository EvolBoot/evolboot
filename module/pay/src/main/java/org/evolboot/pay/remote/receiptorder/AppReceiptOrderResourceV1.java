package org.evolboot.pay.remote.receiptorder;

import org.evolboot.core.annotation.ApiClient;
import org.evolboot.pay.domain.receiptorder.ReceiptOrderAppService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 第三方代收订单
 *
 * @author evol
 */
@Slf4j
@RestController
@RequestMapping("/v1/api/receipt-order")
@Tag(name = "第三方代收订单", description = "第三方代收订单")
@ApiClient
public class AppReceiptOrderResourceV1 {

    private final ReceiptOrderAppService service;

    public AppReceiptOrderResourceV1(ReceiptOrderAppService service) {
        this.service = service;
    }


    /*

    @Operation(summary = "查询第三方代收订单")
    @GetMapping("")
    public ResponseModel<List<ReceiptOrderLocaleResponse>> findAll(
    ) {
        List<ReceiptOrder> result = service.findAll();
        return ResponseModel.ok(ReceiptOrderLocaleResponse.of(result));
    }

    @Operation(summary = "查询第三方代收订单")
    @GetMapping("")
    public ResponseModel<Page<ReceiptOrderLocaleResponse>> page(
       @RequestParam(name = "page", defaultValue = "1") Integer page,
       @RequestParam(name = "limit", defaultValue = "20") Integer limit
    ) {
       ReceiptOrderQuery query = ReceiptOrderQuery
                .builder()
                .page(page)
                .limit(limit)
                .build();
        Page<ReceiptOrder> result = service.page(query);
        return ResponseModel.ok(ReceiptOrderLocaleResponse.of(result));
    }

    */

}
