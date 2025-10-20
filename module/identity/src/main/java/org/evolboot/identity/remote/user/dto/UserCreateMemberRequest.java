package org.evolboot.identity.remote.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.evolboot.identity.IdentityI18nMessage;
import org.evolboot.identity.domain.user.entity.UserType;
import org.evolboot.identity.domain.user.service.UserCreateFactory;
import org.evolboot.shared.lang.UserIdentity;

/**
 * @author evol
 */
@Data
public class UserCreateMemberRequest {
    @NotEmpty(message = IdentityI18nMessage.User.USERNAME_NOT_EMPTY)
    @Schema(title = "用户名", example = "evol")
    private String username;

    @NotEmpty(message = IdentityI18nMessage.User.PASSWORD_NOT_EMPTY)
    @Schema(title = "密码", example = "ZX1Oqhh/Sjnlv7oa5U8mj7ubt5YXcThTAeOcQI0BfI6+bg4goxBQjakLbLbI6u2OnEkbsOkv4YGgOOi+1gnRNMgdYwdUqWfL99mmwhHAksSlQNR3Byu7a5GJeS3G2abyf4Gu2TayRX8xFkSwv1ze+PioM+fvITC6Zmm38g7ClLw=")
    private String password;

    private UserType userType;

    private Long inviterUserId;


    public UserCreateFactory.Request to(String ip) {
        return UserCreateFactory.Request
                .builder()
                .username(username)
                .originalPassword(password)
                .userIdentity(UserIdentity.ROLE_MEMBER)
                .inviterUserId(inviterUserId)
                .userType(userType)
                .registerIp(ip)
                .build();
    }

}
