package org.evolboot.bffapp.remote;

import com.google.common.collect.Maps;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.bffapp.domain.BffAppQuery;
import org.evolboot.bffapp.domain.BffAppService;
import org.evolboot.bffapp.domain.BffUser;
import org.evolboot.bffapp.remote.response.AppConfigResponse;
import org.evolboot.configuration.domain.config.ConfigAppService;
import org.evolboot.configuration.domain.config.about.AboutConfig;
import org.evolboot.configuration.domain.config.system.SystemConfig;
import org.evolboot.core.annotation.ApiClient;
import org.evolboot.core.data.Page;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.core.util.ExtendDateUtil;
import org.evolboot.core.util.JsonUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author evol
 */
@Slf4j
@RestController
@RequestMapping("/v1/api/bff")
@Tag(name = "APP专用", description = "APP专用")
@ApiClient
public class BffAppResourceV1 {

    private final ConfigAppService configAppService;

    private final BffAppService service;

    public BffAppResourceV1(ConfigAppService configAppService, BffAppService service) {
        this.configAppService = configAppService;
        this.service = service;
    }


    @Operation(summary = "测试")
    @GetMapping("/test")
    public ResponseModel<?> test(
    ) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("date1", JsonUtil.stringify(new Date()));
        map.put("date2", new Date());
        map.put("num", BigDecimal.valueOf(100L));

        return ResponseModel.ok(map);
    }


    @Operation(summary = "测试异步")
    @GetMapping("/test-async")
    public ResponseModel<?> testAsync(
    ) throws ExecutionException, InterruptedException {
        String xx = "这是啥?";
        CompletableFuture.supplyAsync(() -> {
            System.out.println("调用时:" + Thread.currentThread().getName());
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            AboutConfig byKey = configAppService.getByKey(AboutConfig.KEY, AboutConfig.class);
            return byKey.getLocales().get(0).getPrivacy();
        }).thenAcceptAsync(s -> {
            System.out.println("回调时:" + Thread.currentThread().getName());
            System.out.println(s + xx + ExtendDateUtil.now());
        });
//        System.out.println(stringCompletableFuture.get());
        return ResponseModel.ok();
    }


    @Operation(summary = "APP配置")
    @GetMapping("/configurations/app-config")
    public ResponseModel<AppConfigResponse> configurations(
    ) {
        AboutConfig aboutConfig = configAppService.getByKey(AboutConfig.KEY, AboutConfig.class);
        SystemConfig systemConfig = configAppService.getByKey(SystemConfig.KEY, SystemConfig.class);
        return ResponseModel.ok(AppConfigResponse.of(aboutConfig, systemConfig));
    }


    @Operation(summary = "查找用户")
    @GetMapping("/user")
    public ResponseModel<Page<BffUser>> findUser(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "limit", defaultValue = "20") Integer limit

    ) {
        Page<BffUser> bffUsers = service.findUser(BffAppQuery.builder()
                .page(page)
                .limit(limit)
                .build());
        return ResponseModel.ok((bffUsers));
    }
}
