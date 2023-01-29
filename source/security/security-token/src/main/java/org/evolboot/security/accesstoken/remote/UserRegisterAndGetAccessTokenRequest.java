package org.evolboot.security.accesstoken.remote;

import org.evolboot.identity.domain.user.UserRegisterService;
import org.evolboot.shared.lang.DeviceType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author evol
 */
@Data
public class UserRegisterAndGetAccessTokenRequest {

    @Schema(
            description = "密码",
            example =
                    "ZX1Oqhh/Sjnlv7oa5U8mj7ubt5YXcThTAeOcQI0BfI6+bg4goxBQjakLbLbI6u2OnEkbsOkv4YGgOOi+1gnRNMgdYwdUqWfL99mmwhHAksSlQNR3Byu7a5GJeS3G2abyf4Gu2TayRX8xFkSwv1ze+PioM+fvITC6Zmm38g7ClLw=")
    private String password;

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

    private Long inviterUserId;

    private DeviceType deviceType;

    public UserRegisterService.Request to(String ip) {
        return new UserRegisterService.Request(
                mobilePrefix,
                mobile,
                password,
                captchaToken,
                captchaCode,
                email,
                ip,
                inviterUserId,
                deviceType
        );
    }


}
