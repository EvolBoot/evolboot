package org.evolboot.pay.remote.paygatewayaccount;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.annotation.ApiClient;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.pay.domain.paygatewayaccount.PayGatewayAccountQueryService;
import org.evolboot.pay.domain.paygatewayaccount.entity.PayGatewayAccount;
import org.evolboot.pay.domain.paygatewayaccount.PayGatewayAccountAppService;
import org.evolboot.pay.domain.paygatewayaccount.dto.PayGatewayAccountQueryRequest;
import org.evolboot.pay.remote.paygatewayaccount.dto.PayGatewayAccountLocaleResponse;
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
@RequestMapping("/v1/api/pay-gateway-account")
@Tag(name = "支付网关账户", description = "支付网关账户")
@ApiClient
public class AppPayGatewayAccountResourceV1 {

    private final PayGatewayAccountAppService service;
    private final PayGatewayAccountQueryService queryService;


    private final PayGatewayAccountQueryRequest defaultQuery = PayGatewayAccountQueryRequest.builder().enable(true).build();


    public AppPayGatewayAccountResourceV1(PayGatewayAccountAppService service, PayGatewayAccountQueryService queryService) {
        this.service = service;
        this.queryService = queryService;
    }


    @Operation(summary = "查询支付网关账户")
    @GetMapping("")
    public ResponseModel<List<PayGatewayAccountLocaleResponse>> findAll(
    ) {
        List<PayGatewayAccount> result = queryService.findAll(defaultQuery);
        return ResponseModel.ok(PayGatewayAccountLocaleResponse.of(result));
    }


    /*

    @Operation(summary = "查询支付网关账户")
    @GetMapping("")
    public ResponseModel<List<PayGatewayAccountLocaleResponse>> findAll(
    ) {
        List<PayGatewayAccount> result = queryService.findAll();
        return ResponseModel.ok(PayGatewayAccountLocaleResponse.of(result));
    }

    @Operation(summary = "查询支付网关账户")
    @GetMapping("")
    public ResponseModel<Page<PayGatewayAccountLocaleResponse>> page(
       @RequestParam(name = "page", defaultValue = "1") Integer page,
       @RequestParam(name = "limit", defaultValue = "20") Integer limit
    ) {
       PayGatewayAccountQuery query = PayGatewayAccountQuery
                .builder()
                .page(page)
                .limit(limit)
                .build();
        Page<PayGatewayAccount> result = queryService.page(query);
        return ResponseModel.ok(PayGatewayAccountLocaleResponse.of(result));
    }

    */

}
