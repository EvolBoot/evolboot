package org.evolboot.system.remote.banner;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.annotation.ApiClient;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.system.domain.banner.entity.Banner;
import org.evolboot.system.domain.banner.BannerAppService;
import org.evolboot.system.domain.banner.service.BannerQuery;
import org.evolboot.system.remote.banner.dto.BannerLocaleResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * banner
 *
 * @author evol
 */
@Slf4j
@RestController
@RequestMapping("/v1/api")
@Tag(name = "banner", description = "banner")
@ApiClient
public class AppBannerResourceV1 {

    private final BannerAppService service;

    public AppBannerResourceV1(BannerAppService service) {
        this.service = service;
    }


    @Operation(summary = "查询banner")
    @GetMapping("/banner")
    public ResponseModel<List<BannerLocaleResponse>> findAll(
    ) {
        List<Banner> result = service.findAll(BannerQuery.builder().show(true).build());
        return ResponseModel.ok(BannerLocaleResponse.of(result));
    }
/*
    @Operation(summary = "查询banner")
    @GetMapping("/banner")
    public ResponseModel<Page<BannerLocaleResponse>> page(
       @RequestParam(name = "page", defaultValue = "1") Integer page,
       @RequestParam(name = "limit", defaultValue = "20") Integer limit
    ) {
       BannerQuery query = BannerQuery
                .builder()
                .page(page)
                .limit(limit)
                .build();
        Page<Banner> result = service.page(query);
        return ResponseModel.ok(BannerLocaleResponse.of(result));
    }

    */

}
