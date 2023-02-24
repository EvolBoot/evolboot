package org.evolboot.pay.remote.releasedorder;

import org.evolboot.core.annotation.AdminClient;
import org.evolboot.pay.domain.releasedorder.ReleasedOrderAppService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 代付订单
 *
 * @author evol
 */
@Slf4j
@RestController
@RequestMapping("/v1/admin/pay")
@Tag(name = "代付订单", description = "代付订单")
@AdminClient
public class AdminReleasedOrderResourceV1 {

    private final ReleasedOrderAppService service;

    public AdminReleasedOrderResourceV1(ReleasedOrderAppService service) {
        this.service = service;
    }

/*

    @Operation(summary = "创建代付订单")
    @OperationLog("创建代付订单")
    @PostMapping("/released-order")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_CREATE)
    public ResponseModel<?> create(
            @RequestBody @Valid
                    ReleasedOrderCreateRequest request
    ) {
        ReleasedOrder releasedOrder = service.create(request);
        return ResponseModel.ok(new DomainId(releasedOrder.id()));
    }


    @Operation(summary = "删除代付订单")
    @OperationLog("删除代付订单")
    @DeleteMapping("/released-order/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_DELETE)
    public ResponseModel<?> delete(
            @PathVariable("id") String id
    ) {
        service.delete(id);
        return ResponseModel.ok();
    }


    @Operation(summary = "修改代付订单")
    @OperationLog("修改代付订单")
    @PutMapping("/released-order/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_UPDATE)
    public ResponseModel<?> update(
            @PathVariable("id") String id,
            @RequestBody @Valid
                    ReleasedOrderUpdateRequest request
    ) {
        service.update(id, request);
        return ResponseModel.ok();
    }

    @Operation(summary = "查询代付订单")
    @OperationLog("查询代付订单")
    @GetMapping("/released-order")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_PAGE)
    public ResponseModel<Page<ReleasedOrder>> page(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "limit", defaultValue = "20") Integer limit
    ) {
        ReleasedOrderQuery query = ReleasedOrderQuery
                .builder()
                .page(page)
                .limit(limit)
                .build();
        Page<ReleasedOrder> response = service.page(query);
        return ResponseModel.ok(response);
    }


    @Operation(summary = "查询单个代付订单")
    @OperationLog("查询单个代付订单")
    @GetMapping("/released-order/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_SINGLE)
    public ResponseModel<ReleasedOrder> get(
            @PathVariable("id") String id
    ) {
        return ResponseModel.ok(service.findById(id));
    }
*/

}
