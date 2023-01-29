package org.evolboot.content.remote.startuppage;

import org.evolboot.content.domain.startuppage.StartupPage;
import org.evolboot.content.domain.startuppage.StartupPageAppService;
import org.evolboot.content.domain.startuppage.StartupPageQuery;
import org.evolboot.core.annotation.ApiClient;
import org.evolboot.core.remote.ResponseModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 启动页
 *
 * @author evol
 * 
 */
@Slf4j
@RestController
@RequestMapping("/v1/api/content")
@Tag(name = "启动页", description = "启动页")
@ApiClient
public class AppStartupPageResourceV1 {

    private final StartupPageAppService service;

    public AppStartupPageResourceV1(StartupPageAppService service) {
        this.service = service;
    }


    @Operation(summary = "查询启动页")
    @GetMapping("/startup-page")
    public ResponseModel<List<StartupPageLocaleResponse>> findAll(
    ) {
        List<StartupPage> result = service.findAll(StartupPageQuery.builder().enable(true).build());
        return ResponseModel.ok(StartupPageLocaleResponse.of(result));
    }

    /*

    @Operation(summary = "查询启动页")
    @GetMapping("/startup-page")
    public ResponseModel<List<StartupPageLocaleResponse>> findAll(
    ) {
        List<StartupPage> result = service.findAll();
        return ResponseModel.ok(StartupPageLocaleResponse.of(result));
    }

    @Operation(summary = "查询启动页")
    @GetMapping("/startup-page")
    public ResponseModel<Page<StartupPageLocaleResponse>> page(
       @RequestParam(name = "page", defaultValue = "1") Integer page,
       @RequestParam(name = "limit", defaultValue = "20") Integer limit
    ) {
       StartupPageQuery query = StartupPageQuery
                .builder()
                .page(page)
                .limit(limit)
                .build();
        Page<StartupPage> result = service.page(query);
        return ResponseModel.ok(StartupPageLocaleResponse.of(result));
    }

    */

}
