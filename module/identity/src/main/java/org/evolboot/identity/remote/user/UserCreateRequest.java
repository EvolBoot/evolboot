package org.evolboot.identity.remote.user;

import org.evolboot.identity.IdentityI18nMessage;
import org.evolboot.identity.domain.user.service.UserCreateFactory;
import org.evolboot.identity.domain.user.UserType;
import org.evolboot.shared.lang.UserIdentity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author evol
 */
@Data
public class UserCreateRequest {
    @NotEmpty(message = IdentityI18nMessage.User.USERNAME_NOT_EMPTY)
    @Schema(description = "用户名", example = "zhangsan")
    private String username;

    @NotEmpty(message = IdentityI18nMessage.User.PASSWORD_NOT_EMPTY)
    @Schema(description = "密码", example = "ZX1Oqhh/Sjnlv7oa5U8mj7ubt5YXcThTAeOcQI0BfI6+bg4goxBQjakLbLbI6u2OnEkbsOkv4YGgOOi+1gnRNMgdYwdUqWfL99mmwhHAksSlQNR3Byu7a5GJeS3G2abyf4Gu2TayRX8xFkSwv1ze+PioM+fvITC6Zmm38g7ClLw=")
    private String password;

    private UserIdentity userIdentity;

    private UserType userType;

    private Long inviterUserId;

    public UserCreateFactory.Request to(String ip) {
        return UserCreateFactory.Request
                .builder()
                .username(username)
                .encodePassword(password)
                .userIdentity(userIdentity)
                .inviterUserId(inviterUserId)
                .userType(userType)
                .registerIp(ip)
                .build();
    }

}
