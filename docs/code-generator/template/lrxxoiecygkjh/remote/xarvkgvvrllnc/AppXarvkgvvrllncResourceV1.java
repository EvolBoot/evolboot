package projectPackage.lrxxoiecygkjh.remote.xarvkgvvrllnc;

import projectPackage.core.annotation.ApiClient;
import projectPackage.core.annotation.OperationLog;
import projectPackage.core.remote.DomainId;
import projectPackage.core.remote.ResponseModel;
import projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.Xarvkgvvrllnc;
import projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.XarvkgvvrllncAppService;
import projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.DefaultXarvkgvvrllncAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import projectPackage.core.data.Page;
import projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.XarvkgvvrllncQuery;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * 模板
 *
 * @author authorxRQXP
 * @date datePlaceholder
 */
@Slf4j
@RestController
@RequestMapping("/v1/api/lh6wf4pd9y/irkdf3nfva")
@Tag(name = "模板", description = "模板")
@ApiClient
public class AppXarvkgvvrllncResourceV1 {

    private final XarvkgvvrllncAppService service;

    public AppXarvkgvvrllncResourceV1(XarvkgvvrllncAppService service) {
        this.service = service;
    }


    /*

    @Operation(summary = "查询模板")
    @GetMapping("")
    public ResponseModel<List<XarvkgvvrllncLocaleResponse>> findAll(
    ) {
        List<Xarvkgvvrllnc> result = service.findAll();
        return ResponseModel.ok(XarvkgvvrllncLocaleResponse.of(result));
    }

    @Operation(summary = "查询模板")
    @GetMapping("")
    public ResponseModel<Page<XarvkgvvrllncLocaleResponse>> page(
       @RequestParam(name = "page", defaultValue = "1") Integer page,
       @RequestParam(name = "limit", defaultValue = "20") Integer limit
    ) {
       XarvkgvvrllncQuery query = XarvkgvvrllncQuery
                .builder()
                .page(page)
                .limit(limit)
                .build();
        Page<Xarvkgvvrllnc> result = service.page(query);
        return ResponseModel.ok(XarvkgvvrllncLocaleResponse.of(result));
    }

    */

}
