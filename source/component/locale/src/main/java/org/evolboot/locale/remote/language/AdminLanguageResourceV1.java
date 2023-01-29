package org.evolboot.locale.remote.language;

import org.evolboot.core.annotation.AdminClient;
import org.evolboot.core.annotation.OperationLog;
import org.evolboot.core.remote.DomainId;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.locale.domain.language.Language;
import org.evolboot.locale.domain.language.LanguageAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.evolboot.locale.LocaleAccessAuthorities.Language.*;
import static org.evolboot.security.api.access.AccessAuthorities.HAS_ROLE_ADMIN;
import static org.evolboot.security.api.access.AccessAuthorities.or;

/**
 * @author evol
 */
@RestController
@RequestMapping("/v1/admin")
@Tag(name = "Language", description = "语言管理")
@AdminClient
public class AdminLanguageResourceV1 {

    private final LanguageAppService service;

    public AdminLanguageResourceV1(LanguageAppService service) {
        this.service = service;
    }

    @Operation(summary = "创建语言")
    @OperationLog("创建语言")
    @PostMapping("/languages")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_CREATE)
    public ResponseModel<?> create(
            @RequestBody @Valid
                    LanguageCreateRequest request
    ) {
        Long id = service.create(
                request
        );
        return ResponseModel.ok(new DomainId(id));
    }


    @Operation(summary = "删除语言")
    @OperationLog("删除语言")
    @DeleteMapping("/languages/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_DELETE)
    public ResponseModel<?> delete(
            @PathVariable("id") Long id
    ) {
        service.delete(id);
        return ResponseModel.ok();
    }

    @Operation(summary = "修改语言")
    @OperationLog("修改语言")
    @PutMapping("/languages/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_UPDATE)
    public ResponseModel<?> update(
            @PathVariable("id") Long id,
            @RequestBody @Valid
                    LanguageUpdateRequest request
    ) {
        service.update(id, request);
        return ResponseModel.ok();
    }

    @Operation(summary = "启用语言")
    @OperationLog("启用语言")
    @PutMapping("/languages/{id}/enable")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_STATUS_ENABLE)
    public ResponseModel<?> enable(
            @PathVariable("id") Long id
    ) {
        service.enable(id);
        return ResponseModel.ok();
    }

    @Operation(summary = "停用语言")
    @OperationLog("停用语言")
    @PutMapping("/languages/{id}/disable")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_STATUS_DISABLE)
    public ResponseModel<?> disable(
            @PathVariable("id") Long id
    ) {
        service.disable(id);
        return ResponseModel.ok();
    }

    @Operation(summary = "查询语言")
    @OperationLog("查询语言")
    @GetMapping("/languages")
    public ResponseModel<List<Language>> page(
    ) {
        return ResponseModel.ok(service.list());
    }

    @Operation(summary = "查询单个语言")
    @OperationLog("查询单个语言")
    @GetMapping("/languages/{id}")
    public ResponseModel<Language> get(
            @PathVariable("id") Long id
    ) {
        return ResponseModel.ok(service.findById(id));
    }

}
