package org.evolboot.identity.remote.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.evolboot.identity.IdentityI18nMessage;

import javax.validation.constraints.NotEmpty;

/**
 * @author evol
 */
@Data
public class UserPasswordUpdateRequest {

    @NotEmpty(message = IdentityI18nMessage.User.OLD_PASSWORD_NOT_EMPTY)
    @Schema(description = "原密码", example = "123456")
    String oldPassword;

    @NotEmpty(message = IdentityI18nMessage.User.NEW_PASSWORD_NOT_EMPTY)
    @Schema(description = "密码", example = "123456")
    String newPassword;

    @NotEmpty(message = IdentityI18nMessage.User.CONFIRM_PASSWORD_NOT_EMPTY)
    @Schema(description = "确认密码", example = "123456")
    String confirmPassword;

}
