package org.evolboot.identity.remote.user;

import lombok.Getter;
import lombok.Setter;
import org.evolboot.identity.domain.user.service.UserSecurityPasswordResetService;

/**
 * @author evol
 */
@Setter
@Getter
public class AppSecurityPasswordResetRequest {

    private UserSecurityPasswordResetService.ValidationType validationType;
    private String captchaToken;
    private String captchaCode;
    private String password;
    private String internalCode;

    public UserSecurityPasswordResetService.Request to(Long userId, String internalCode) {
        return UserSecurityPasswordResetService.Request
                .builder()
                .validationType(validationType)
                .captchaCode(captchaCode)
                .captchaToken(captchaToken)
                .encodePassword(password)
                .internalCode(internalCode)
                .userId(userId)
                .build();
    }

}
