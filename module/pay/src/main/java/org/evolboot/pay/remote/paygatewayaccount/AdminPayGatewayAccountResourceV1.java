package org.evolboot.pay.remote.paygatewayaccount;

import org.evolboot.core.annotation.AdminClient;
import org.evolboot.core.annotation.OperationLog;
import org.evolboot.core.data.Page;
import org.evolboot.core.remote.DomainId;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.pay.domain.paygatewayaccount.PayGatewayAccount;
import org.evolboot.pay.domain.paygatewayaccount.PayGatewayAccountAppService;
import org.evolboot.pay.domain.paygatewayaccount.PayGatewayAccountQuery;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.evolboot.security.api.access.AccessAuthorities.HAS_ROLE_ADMIN;
import static org.evolboot.security.api.access.AccessAuthorities.or;
import static org.evolboot.pay.PayAccessAuthorities.PayGatewayAccount.*;

/**
 * 支付网关账户
 *
 * @author evol
 */
@Slf4j
@RestController
@RequestMapping("/v1/admin/pay-gateway-account")
@Tag(name = "支付网关账户", description = "支付网关账户")
@AdminClient
public class AdminPayGatewayAccountResourceV1 {

    private final PayGatewayAccountAppService service;

    public AdminPayGatewayAccountResourceV1(PayGatewayAccountAppService service) {
        this.service = service;
    }


    @Operation(summary = "创建支付网关账户")
    @OperationLog("创建支付网关账户")
    @PostMapping("")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_CREATE)
    public ResponseModel<?> create(
            @RequestBody @Valid
                    PayGatewayAccountCreateRequest request
    ) {
        PayGatewayAccount payGatewayAccount = service.create(request);
        return ResponseModel.ok(new DomainId(payGatewayAccount.id()));
    }


    @Operation(summary = "删除支付网关账户")
    @OperationLog("删除支付网关账户")
    @DeleteMapping("/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_DELETE)
    public ResponseModel<?> delete(
            @PathVariable("id") Long id
    ) {
        service.delete(id);
        return ResponseModel.ok();
    }


    @Operation(summary = "修改支付网关账户")
    @OperationLog("修改支付网关账户")
    @PutMapping("/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_UPDATE)
    public ResponseModel<?> update(
            @PathVariable("id") Long id,
            @RequestBody @Valid
                    PayGatewayAccountUpdateRequest request
    ) {
        service.update(id, request);
        return ResponseModel.ok();
    }

    @Operation(summary = "查询支付网关账户")
    @OperationLog("查询支付网关账户")
    @GetMapping("")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_PAGE)
    public ResponseModel<Page<PayGatewayAccount>> page(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "limit", defaultValue = "20") Integer limit
    ) {
        PayGatewayAccountQuery query = PayGatewayAccountQuery
                .builder()
                .page(page)
                .limit(limit)
                .build();
        Page<PayGatewayAccount> response = service.page(query);
        return ResponseModel.ok(response);
    }


    @Operation(summary = "查询单个支付网关账户")
    @OperationLog("查询单个支付网关账户")
    @GetMapping("/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_SINGLE)
    public ResponseModel<PayGatewayAccount> get(
            @PathVariable("id") Long id
    ) {
        return ResponseModel.ok(service.findById(id));
    }

}
