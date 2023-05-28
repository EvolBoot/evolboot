package org.evolboot.security.accesstoken.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.util.Assert;
import org.evolboot.identity.IdentityI18nMessage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author evol
 */

@Slf4j
@Getter
@NoArgsConstructor
public class AccessToken {

    private String token;

    private Long principalId;

    private String loginIp;

    private Set<String> authorities = new HashSet<>();

    @Builder
    public AccessToken(Long principalId, Set<String> authorities) {
        Assert.notNull(principalId, IdentityI18nMessage.User.userIdNotNull());
        this.principalId = principalId;
        this.authorities = authorities.stream().filter(authorization -> !authorization.isBlank()).collect(Collectors.toSet());
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }
}
