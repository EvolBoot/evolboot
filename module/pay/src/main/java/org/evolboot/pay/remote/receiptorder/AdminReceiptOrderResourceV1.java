package org.evolboot.pay.remote.receiptorder;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.annotation.AdminClient;
import org.evolboot.pay.domain.receiptorder.ReceiptOrderAppService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 第三方代收订单
 *
 * @author evol
 */
@Slf4j
@RestController
@RequestMapping("/v1/admin/receipt-order")
@Tag(name = "第三方代收订单", description = "第三方代收订单")
@AdminClient
public class AdminReceiptOrderResourceV1 {

    private final ReceiptOrderAppService service;

    public AdminReceiptOrderResourceV1(ReceiptOrderAppService service) {
        this.service = service;
    }
/*


    @Operation(summary = "创建第三方代收订单")
    @OperationLog("创建第三方代收订单")
    @PostMapping("")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_CREATE)
    public ResponseModel<?> create(
            @RequestBody @Valid
                    ReceiptOrderCreateRequest request
    ) {
        ReceiptOrder receiptOrder = service.create(request);
        return ResponseModel.ok(new DomainId(receiptOrder.id()));
    }


    @Operation(summary = "删除第三方代收订单")
    @OperationLog("删除第三方代收订单")
    @DeleteMapping("/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_DELETE)
    public ResponseModel<?> delete(
            @PathVariable("id") String id
    ) {
        service.delete(id);
        return ResponseModel.ok();
    }


    @Operation(summary = "修改第三方代收订单")
    @OperationLog("修改第三方代收订单")
    @PutMapping
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_UPDATE)
    public ResponseModel<?> update(
            @RequestBody @Valid
                    ReceiptOrderUpdateRequest request
    ) {
        service.update( request);
        return ResponseModel.ok();
    }

    @Operation(summary = "查询第三方代收订单")
    @OperationLog("查询第三方代收订单")
    @GetMapping("")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_PAGE)
    public ResponseModel<Page<ReceiptOrder>> page(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "limit", defaultValue = "20") Integer limit
    ) {
        ReceiptOrderQuery query = ReceiptOrderQuery
                .builder()
                .page(page)
                .limit(limit)
                .build();
        Page<ReceiptOrder> response = service.page(query);
        return ResponseModel.ok(response);
    }


    @Operation(summary = "查询单个第三方代收订单")
    @OperationLog("查询单个第三方代收订单")
    @GetMapping("/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_SINGLE)
    public ResponseModel<ReceiptOrder> get(
            @PathVariable("id") String id
    ) {
        return ResponseModel.ok(service.findById(id));
    }
*/

}
