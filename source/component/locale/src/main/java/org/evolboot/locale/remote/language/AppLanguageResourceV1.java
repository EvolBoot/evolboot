package org.evolboot.locale.remote.language;

import org.evolboot.core.annotation.ApiClient;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.locale.domain.language.Language;
import org.evolboot.locale.domain.language.LanguageAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author evol
 */
@RestController
@RequestMapping("/v1/api")
@Tag(name = "Language", description = "语言管理")
@ApiClient
public class AppLanguageResourceV1 {

    private final LanguageAppService service;

    public AppLanguageResourceV1(LanguageAppService service) {
        this.service = service;
    }

    @Operation(summary = "查询语言")
    @GetMapping("/languages")
    public ResponseModel<List<Language>> page(
    ) {
        return ResponseModel.ok(service.findAllByEnableIsTrue());
    }

    @Operation(summary = "查询单个语言")
    @GetMapping("/languages/{id}")
    public ResponseModel<Language> get(
            @PathVariable("id") Long id
    ) {
        return ResponseModel.ok(service.findById(id));
    }

}
