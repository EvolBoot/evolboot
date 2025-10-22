package org.evolboot.pay.remote.payinorder;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.annotation.AdminClient;
import org.evolboot.core.annotation.OperationLog;
import org.evolboot.core.remote.DomainId;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.pay.domain.payinorder.PayinOrderAppService;
import org.evolboot.pay.domain.payinorder.PayinOrderQueryService;
import org.evolboot.pay.domain.payinorder.entity.PayinOrder;
import org.evolboot.pay.domain.payinorder.service.PayinOrderCreateFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.evolboot.pay.PayAccessAuthorities.PayinOrder.*;
import static org.evolboot.security.api.access.AccessAuthorities.HAS_ROLE_SUPER_ADMIN;
import static org.evolboot.security.api.access.AccessAuthorities.OR;

/**
 * 第三方代收订单
 *
 * @author evol
 */
@Slf4j
@RestController
@RequestMapping("/admin/v1/payin-order")
@Tag(name = "第三方代收订单", description = "第三方代收订单")
@AdminClient
public class AdminPayinOrderResourceV1 {

    private final PayinOrderAppService service;
    private final PayinOrderQueryService queryService;

    public AdminPayinOrderResourceV1(PayinOrderAppService service, PayinOrderQueryService queryService) {
        this.service = service;
        this.queryService = queryService;
    }



    @Operation(summary = "创建第三方代收订单")
    @OperationLog("创建第三方代收订单")
    @PostMapping("")
    @PreAuthorize(HAS_ROLE_SUPER_ADMIN + OR + HAS_CREATE)
    public ResponseModel<?> create(
            @RequestBody
            PayinOrderCreateFactory.Request request
    ) {
        PayinOrder payinOrder = service.create(request);
        return ResponseModel.ok(new DomainId(payinOrder.id()));
    }

/*
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
