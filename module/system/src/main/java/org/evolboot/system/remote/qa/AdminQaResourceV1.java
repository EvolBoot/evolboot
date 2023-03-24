package org.evolboot.system.remote.qa;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.system.domain.qa.Qa;
import org.evolboot.system.domain.qa.QaAppService;
import org.evolboot.system.domain.qa.QaQuery;
import org.evolboot.core.annotation.AdminClient;
import org.evolboot.core.annotation.OperationLog;
import org.evolboot.core.data.Page;
import org.evolboot.core.remote.DomainId;
import org.evolboot.core.remote.ResponseModel;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.evolboot.system.SystemAccessAuthorities.Qa.*;
import static org.evolboot.security.api.access.AccessAuthorities.HAS_ROLE_ADMIN;
import static org.evolboot.security.api.access.AccessAuthorities.or;

/**
 * QA
 *
 * @author evol
 * 
 */
@Slf4j
@RestController
@RequestMapping("/v1/admin/qa")
@Tag(name = "QA", description = "QA")
@AdminClient
public class AdminQaResourceV1 {

    private final QaAppService service;

    public AdminQaResourceV1(QaAppService service) {
        this.service = service;
    }


    @Operation(summary = "创建QA")
    @OperationLog("创建QA")
    @PostMapping("")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_CREATE)
    public ResponseModel<?> create(
            @RequestBody @Valid
                    QaCreateRequest request
    ) {
        Qa qa = service.create(request);
        return ResponseModel.ok(new DomainId(qa.id()));
    }


    @Operation(summary = "删除QA")
    @OperationLog("删除QA")
    @DeleteMapping("/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_DELETE)
    public ResponseModel<?> delete(
            @PathVariable("id") Long id
    ) {
        service.delete(id);
        return ResponseModel.ok();
    }


    @Operation(summary = "修改QA")
    @OperationLog("修改QA")
    @PutMapping("/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_UPDATE)
    public ResponseModel<?> update(
            @PathVariable("id") Long id,
            @RequestBody @Valid
                    QaUpdateRequest request
    ) {
        service.update(id, request);
        return ResponseModel.ok();
    }

    @Operation(summary = "查询QA")
    @GetMapping("")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_PAGE)
    public ResponseModel<Page<Qa>> page(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "limit", defaultValue = "20") Integer limit
    ) {
        QaQuery query = QaQuery
                .builder()
                .page(page)
                .limit(limit)
                .build();
        Page<Qa> response = service.page(query);
        return ResponseModel.ok(response);
    }


    @Operation(summary = "查询单个QA")
    @GetMapping("/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_SINGLE)
    public ResponseModel<Qa> get(
            @PathVariable("id") Long id
    ) {
        return ResponseModel.ok(service.findById(id));
    }

}
