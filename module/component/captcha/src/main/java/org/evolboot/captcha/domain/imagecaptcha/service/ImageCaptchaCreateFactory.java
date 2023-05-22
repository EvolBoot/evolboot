package org.evolboot.captcha.domain.imagecaptcha.service;

import com.wf.captcha.ArithmeticCaptcha;
import lombok.Builder;
import lombok.Getter;
import org.evolboot.captcha.domain.imagecaptcha.entity.ImageCaptcha;
import org.evolboot.captcha.domain.imagecaptcha.ImageCaptchaConfiguration;
import org.evolboot.captcha.domain.imagecaptcha.repository.ImageCaptchaRepository;
import org.springframework.stereotype.Service;

/**
 * @author evol
 */
@Service
public class ImageCaptchaCreateFactory {

    private ImageCaptchaRepository repository;

    public ImageCaptchaCreateFactory(ImageCaptchaRepository repository) {
        this.repository = repository;
    }

    public Response create(Integer width, Integer height, String ip) {
        ArithmeticCaptcha captcha = generate(width, height);
        ImageCaptcha imageCaptcha = ImageCaptcha.builder()
                .code(captcha.text())
                .ip(ip)
                .expires(ImageCaptchaConfiguration.getValue().getDefaultExpires())
                .build();
        repository.save(imageCaptcha);
        return Response.builder()
                .base64(captcha.toBase64())
                .token(imageCaptcha.getToken())
                .expires(imageCaptcha.getExpires())
                .intervals(ImageCaptchaConfiguration.getValue().getDefaultIntervals())
                .build();
    }

    private ArithmeticCaptcha generate(Integer width, Integer height) {
        if (width > ImageCaptchaConfiguration.getValue().getDefaultWidth()) {
            width = ImageCaptchaConfiguration.getValue().getDefaultWidth();
        }
        if (height > ImageCaptchaConfiguration.getValue().getDefaultHeight()) {
            height = ImageCaptchaConfiguration.getValue().getDefaultHeight();
        }
        //获取图片验证码
        // 三个参数分别为宽、高、位数
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(width, height, ImageCaptchaConfiguration.getValue().getLen());
        captcha.getArithmeticString();  // 获取运算的公式：3+2=?
        // 设置字体
//        captcha.setFont(new Font("Verdana", Font.PLAIN, 32));  // 有默认字体，可以不用设置
        // 设置类型，纯数字、纯字母、字母数字混合
        return captcha;
    }

    @Builder
    @Getter
    public static class Response {
        private String token;
        private String base64;
        private long expires;
        private long intervals;
    }

}
