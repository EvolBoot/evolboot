package org.evolboot.security.accesstoken.domain;

import org.evolboot.core.util.Assert;
import org.evolboot.identity.IdentityI18nMessage;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author evol

 */

@Slf4j
@Getter
@NoArgsConstructor
public class AccessToken {

    private String token;

    private Long userId;

    private String loginIp;

    private List<String> authorities = new ArrayList<>();

    @Builder
    public AccessToken(Long userId, List<String> authorities) {
        Assert.notNull(userId, IdentityI18nMessage.User.userIdNotNull());
        this.userId = userId;
        this.authorities = authorities.stream().filter(authorization -> !authorization.isBlank()).collect(Collectors.toList());
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }
}
