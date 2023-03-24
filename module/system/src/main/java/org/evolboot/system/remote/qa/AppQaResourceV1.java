package org.evolboot.system.remote.qa;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.system.domain.qa.Qa;
import org.evolboot.system.domain.qa.QaAppService;
import org.evolboot.core.annotation.ApiClient;
import org.evolboot.core.remote.ResponseModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * QA
 *
 * @author evol
 * 
 */
@Slf4j
@RestController
@RequestMapping("/v1/api/qa")
@Tag(name = "QA", description = "QA")
@ApiClient
public class AppQaResourceV1 {

    private final QaAppService service;

    public AppQaResourceV1(QaAppService service) {
        this.service = service;
    }


    @Operation(summary = "查询QA")
    @GetMapping("")
    public ResponseModel<List<QaLocaleResponse>> findAll(
    ) {
        List<Qa> result = service.findAll();
        return ResponseModel.ok(QaLocaleResponse.of(result));
    }

    /*
    @Operation(summary = "查询QA")
    @GetMapping("")
    public ResponseModel<Page<QaLocaleResponse>> page(
       @RequestParam(name = "page", defaultValue = "1") Integer page,
       @RequestParam(name = "limit", defaultValue = "20") Integer limit
    ) {
       QaQuery query = QaQuery
                .builder()
                .page(page)
                .limit(limit)
                .build();
        Page<Qa> result = service.page(query);
        return ResponseModel.ok(QaLocaleResponse.of(result));
    }

    */

}
