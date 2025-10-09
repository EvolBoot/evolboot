package org.evolboot.identity.remote.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.evolboot.identity.IdentityI18nMessage;
import org.evolboot.identity.domain.user.entity.Gender;
import org.evolboot.identity.domain.user.entity.UserState;
import org.evolboot.identity.domain.user.service.UserCreateFactory;
import org.evolboot.shared.lang.UserIdentity;

import jakarta.validation.constraints.NotEmpty;
import java.util.Set;

/**
 * 创建租户请求
 *
 * @author evol
 */
@Data
public class UserCreateTenantRequest {

    @NotEmpty(message = IdentityI18nMessage.User.USERNAME_NOT_EMPTY)
    @Schema(title = "用户名", example = "tenant001")
    private String username;

    @Schema(title = "昵称", example = "租户名称")
    private String nickname;

    @Schema(title = "手机号", example = "13800138000")
    private String mobile;

    @Schema(title = "邮箱", example = "tenant@example.com")
    private String email;

    @Schema(title = "性别")
    private Gender gender;

    @Schema(title = "状态")
    private UserState state;

    @NotEmpty(message = IdentityI18nMessage.User.PASSWORD_NOT_EMPTY)
    @Schema(title = "密码", example = "ZX1Oqhh/Sjnlv7oa5U8mj7ubt5YXcThTAeOcQI0BfI6+bg4goxBQjakLbLbI6u2OnEkbsOkv4YGgOOi+1gnRNMgdYwdUqWfL99mmwhHAksSlQNR3Byu7a5GJeS3G2abyf4Gu2TayRX8xFkSwv1ze+PioM+fvITC6Zmm38g7ClLw=")
    private String password;

    @Schema(title = "备注")
    private String remark;


    public UserCreateFactory.Request to(String ip) {
        return UserCreateFactory
                .Request
                .builder()
                .username(username)
                .nickname(nickname)
                .encodePassword(password)
                .mobile(mobile)
                .email(email)
                .gender(gender)
                .state(state)
                .remark(remark)
                .userIdentity(UserIdentity.ROLE_TENANT_OWNER)
                .registerIp(ip)
                .build();
    }

}
