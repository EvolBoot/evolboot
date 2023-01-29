package org.evolboot.locale.remote.region;

import org.evolboot.core.annotation.ApiClient;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.locale.domain.region.Region;
import org.evolboot.locale.domain.region.RegionAppService;
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
@Tag(name = "Region", description = "地区管理")
@ApiClient
public class AppRegionResourceV1 {

    private final RegionAppService service;

    public AppRegionResourceV1(RegionAppService service) {
        this.service = service;
    }

    @Operation(summary = "查询地区")
    @GetMapping("/regions")
    public ResponseModel<List<Region>> page(
    ) {
        return ResponseModel.ok(service.findAllByEnableIsTrue());
    }

    @Operation(summary = "查询单个地区")
    @GetMapping("/regions/{id}")
    public ResponseModel<Region> get(
            @PathVariable("id") Long id
    ) {
        return ResponseModel.ok(service.findById(id));
    }

}
