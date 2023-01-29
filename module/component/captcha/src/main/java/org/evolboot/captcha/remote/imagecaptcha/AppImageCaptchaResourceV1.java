package org.evolboot.captcha.remote.imagecaptcha;

import org.evolboot.captcha.domain.imagecaptcha.ImageCaptchaAppService;
import org.evolboot.captcha.domain.imagecaptcha.ImageCaptchaCreateFactory;
import org.evolboot.core.annotation.ApiClient;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.core.util.IpUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author evol
 */
@RestController
@RequestMapping("/v1/api/")
@Tag(name = "验证码", description = "验证码")
@ApiClient
public class AppImageCaptchaResourceV1 {

    private final ImageCaptchaAppService appService;

    public AppImageCaptchaResourceV1(ImageCaptchaAppService appService) {
        this.appService = appService;
    }

    @GetMapping("/image-captcha/{width}/{height}")
    @Operation(summary = "获取图片验证码")
    public ResponseModel<ImageCaptchaCreateFactory.Response> create(
            HttpServletRequest request,
            @RequestParam(defaultValue = "200")
            @PathVariable("width") Integer width,
            @RequestParam(defaultValue = "100")
            @PathVariable("height") Integer height
    ) {
        ImageCaptchaCreateFactory.Response imageCaptchaResponse = appService.create(width, height, IpUtil.getClientIP(request));
        return ResponseModel.ok(imageCaptchaResponse);
    }

}
