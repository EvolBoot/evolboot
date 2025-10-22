package org.evolboot.pay.remote.payoutorder;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.annotation.AdminClient;
import org.evolboot.pay.domain.payoutorder.PayoutOrderAppService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/admin/v1/payout-order")
@Tag(name = "代付订单", description = "代付订单")
@AdminClient
public class AdminPayoutOrderResourceV1 {

    private final PayoutOrderAppService service;

    public AdminPayoutOrderResourceV1(PayoutOrderAppService service) {
        this.service = service;
    }

/*

    @Operation(summary = "创建代付订单")
    @OperationLog("创建代付订单")
    @PostMapping("")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_CREATE)
    public ResponseModel<?> create(
            @RequestBody @Valid
                    PayoutOrderCreateRequest request
    ) {
        PayoutOrder payoutOrder = service.create(request);
        return ResponseModel.ok(new DomainId(payoutOrder.id()));
    }


    @Operation(summary = "删除代付订单")
    @OperationLog("删除代付订单")
    @DeleteMapping("/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_DELETE)
    public ResponseModel<?> delete(
            @PathVariable("id") String id
    ) {
        service.delete(id);
        return ResponseModel.ok();
    }


    @Operation(summary = "修改代付订单")
    @OperationLog("修改代付订单")
    @PutMapping
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_UPDATE)
    public ResponseModel<?> update(
            @RequestBody @Valid
                    PayoutOrderUpdateRequest request
    ) {
        service.update(request);
        return ResponseModel.ok();
    }

    @Operation(summary = "查询代付订单")
    @OperationLog("查询代付订单")
    @GetMapping("")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_PAGE)
    public ResponseModel<Page<PayoutOrder>> page(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "limit", defaultValue = "20") Integer limit
    ) {
        PayoutOrderQuery query = PayoutOrderQuery
                .builder()
                .page(page)
                .limit(limit)
                .build();
        Page<PayoutOrder> response = service.page(query);
        return ResponseModel.ok(response);
    }


    @Operation(summary = "查询单个代付订单")
    @OperationLog("查询单个代付订单")
    @GetMapping("/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_SINGLE)
    public ResponseModel<PayoutOrder> get(
            @PathVariable("id") String id
    ) {
        return ResponseModel.ok(service.findById(id));
    }
*/

}
