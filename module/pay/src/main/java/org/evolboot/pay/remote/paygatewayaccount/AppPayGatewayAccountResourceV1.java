package org.evolboot.pay.remote.paygatewayaccount;

import org.evolboot.core.annotation.ApiClient;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.pay.domain.paygatewayaccount.PayGatewayAccount;
import org.evolboot.pay.domain.paygatewayaccount.PayGatewayAccountAppService;
import org.evolboot.pay.domain.paygatewayaccount.PayGatewayAccountQuery;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 支付网关账户
 *
 * @author evol
 */
@Slf4j
@RestController
@RequestMapping("/v1/api/pay")
@Tag(name = "支付网关账户", description = "支付网关账户")
@ApiClient
public class AppPayGatewayAccountResourceV1 {

    private final PayGatewayAccountAppService service;

    private final PayGatewayAccountQuery defaultQuery = PayGatewayAccountQuery.builder().enable(true).build();


    public AppPayGatewayAccountResourceV1(PayGatewayAccountAppService service) {
        this.service = service;
    }


    @Operation(summary = "查询支付网关账户")
    @GetMapping("/pay-gateway-account")
    public ResponseModel<List<PayGatewayAccountLocaleResponse>> findAll(
    ) {
        List<PayGatewayAccount> result = service.findAll(defaultQuery);
        return ResponseModel.ok(PayGatewayAccountLocaleResponse.of(result));
    }


    /*

    @Operation(summary = "查询支付网关账户")
    @GetMapping("/pay-gateway-account")
    public ResponseModel<List<PayGatewayAccountLocaleResponse>> findAll(
    ) {
        List<PayGatewayAccount> result = service.findAll();
        return ResponseModel.ok(PayGatewayAccountLocaleResponse.of(result));
    }

    @Operation(summary = "查询支付网关账户")
    @GetMapping("/pay-gateway-account")
    public ResponseModel<Page<PayGatewayAccountLocaleResponse>> page(
       @RequestParam(name = "page", defaultValue = "1") Integer page,
       @RequestParam(name = "limit", defaultValue = "20") Integer limit
    ) {
       PayGatewayAccountQuery query = PayGatewayAccountQuery
                .builder()
                .page(page)
                .limit(limit)
                .build();
        Page<PayGatewayAccount> result = service.page(query);
        return ResponseModel.ok(PayGatewayAccountLocaleResponse.of(result));
    }

    */

}
