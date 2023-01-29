package org.evolboot.identity.remote.user;

import org.evolboot.identity.domain.user.UserResetPasswordService;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author evol
 */
@Data
public class UserResetPasswordRequest {


    @Schema(description = "密码", example = "ZX1Oqhh/Sjnlv7oa5U8mj7ubt5YXcThTAeOcQI0BfI6+bg4goxBQjakLbLbI6u2OnEkbsOkv4YGgOOi+1gnRNMgdYwdUqWfL99mmwhHAksSlQNR3Byu7a5GJeS3G2abyf4Gu2TayRX8xFkSwv1ze+PioM+fvITC6Zmm38g7ClLw=")
    private String newPassword;

    @Schema(description = "手机号前缀", example = "+86")
    private String mobilePrefix;

    @Schema(description = "邮箱", example = "aaa2@gmail.com")
    private String email;

    @Schema(description = "手机号", example = "17777777777")
    private String mobile;

    @Schema(description = "验证码Token", example = "85710ea2303141fd91d32147a1411075")
    private String captchaToken;

    @Schema(description = "验证码Code", example = "10086")
    private String captchaCode;


    public UserResetPasswordService.Request to(String ip) {
        return UserResetPasswordService.Request
                .builder()
                .email(email)
                .mobile(mobile)
                .mobilePrefix(mobilePrefix)
                .captchaCode(captchaCode)
                .captchaToken(captchaToken)
                .encodePassword(newPassword)
                .build();
    }


}
