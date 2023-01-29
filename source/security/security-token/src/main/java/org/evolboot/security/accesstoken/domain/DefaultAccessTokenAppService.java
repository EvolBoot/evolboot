package org.evolboot.security.accesstoken.domain;

import org.evolboot.core.event.EventPublisher;
import org.evolboot.identity.domain.user.UserRegisterService;
import org.evolboot.security.accesstoken.acl.port.AccessTokenConfigurationPort;
import org.evolboot.security.accesstoken.domain.authentication.AccessTokenAuthenticationManager;
import org.evolboot.security.accesstoken.domain.authentication.AuthenticationToken;
import org.evolboot.security.accesstoken.domain.authentication.AuthenticationTokenType;
import org.evolboot.security.accesstoken.domain.authentication.googleauthenticator.GoogleAuthenticatorAuthenticationToken;
import org.evolboot.security.accesstoken.domain.authentication.mobilecaptcha.MobileCaptchaAuthenticationToken;
import org.evolboot.security.accesstoken.domain.authentication.usernamepassword.UsernamePasswordAuthenticationToken;
import org.evolboot.security.api.LoginService;
import org.evolboot.security.api.SecurityAccessTokenAppService;
import org.evolboot.shared.lang.UserIdentity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author evol
 */
@Service
public class DefaultAccessTokenAppService implements AccessTokenAppService {

    private final AccessTokenAuthenticationManager accessTokenAuthenticationManager;
    private final EventPublisher eventPublisher;
    private final AccessTokenConfigurationPort accessTokenConfigurationPort;
    private final RegisterUserAndGetAccessToken registerUserAndGetAccessToken;
    private final SecurityAccessTokenAppService securityAccessTokenAppService;

    public DefaultAccessTokenAppService(AccessTokenAuthenticationManager accessTokenAuthenticationManager, EventPublisher eventPublisher, AccessTokenConfigurationPort accessTokenConfigurationPort, RegisterUserAndGetAccessToken registerUserAndGetAccessToken, SecurityAccessTokenAppService securityAccessTokenAppService) {
        this.accessTokenAuthenticationManager = accessTokenAuthenticationManager;
        this.eventPublisher = eventPublisher;
        this.accessTokenConfigurationPort = accessTokenConfigurationPort;
        this.registerUserAndGetAccessToken = registerUserAndGetAccessToken;
        this.securityAccessTokenAppService = securityAccessTokenAppService;
    }

    @Override
    @Transactional
    public AccessToken authenticate(AccessTokenAuthenticateToken accessTokenAuthenticateToken, String loginIp) {

        AuthenticationToken token;
        if (AuthenticationTokenType.MOBILE_CAPTCHA.equals(accessTokenAuthenticateToken.getAuthenticationTokenType())) {
            token = new MobileCaptchaAuthenticationToken(
                    accessTokenAuthenticateToken.getMobilePrefix(),
                    accessTokenAuthenticateToken.getUsername(),
                    accessTokenAuthenticateToken.getMobileCaptchaToken(),
                    accessTokenAuthenticateToken.getMobileCaptchaCode()
            );
        } else if (AuthenticationTokenType.GOOGLE_AUTHENTICATOR.equals(accessTokenAuthenticateToken.getAuthenticationTokenType())) {
            token = new GoogleAuthenticatorAuthenticationToken(
                    accessTokenAuthenticateToken.getUsername(), accessTokenAuthenticateToken.getPassword(), accessTokenAuthenticateToken.getGoogleAuthenticatorCode()
            );
        } else {
            token = new UsernamePasswordAuthenticationToken(
                    accessTokenAuthenticateToken.getUsername(), accessTokenAuthenticateToken.getPassword()
            );
        }
        AccessToken accessToken = accessTokenAuthenticationManager.authenticate(token);
        accessToken.setLoginIp(loginIp);

        kickOutUser(accessToken);// 踢除其他用户下线

        LoginService.Response response = securityAccessTokenAppService.login(new LoginService.Request(accessToken.getUserId(), loginIp, accessToken.getAuthorities()));
        accessToken.setToken(response.getToken());
        return accessToken;
    }

    @Override
    public AccessToken registerAndGetAccessToken(UserRegisterService.Request request) {
        AccessToken accessToken = registerUserAndGetAccessToken.execute(request);
        LoginService.Response response = securityAccessTokenAppService.login(new LoginService.Request(accessToken.getUserId(), request.getRegisterIp(), accessToken.getAuthorities()));
        accessToken.setToken(response.getToken());
        return accessToken;
    }


    private void kickOutUser(AccessToken accessToken) {
        if (!accessTokenConfigurationPort.enableSingleLogin()) {
            return;
        }
        if (accessToken.getAuthorities().contains(UserIdentity.ROLE_STAFF.name()) || accessToken.getAuthorities().contains(UserIdentity.ROLE_ADMIN.name())) {
            return;
        }
        securityAccessTokenAppService.kickOut(accessToken.getUserId());
    }

    @Override
    public void logout(String tokenValue) {
        securityAccessTokenAppService.logout(tokenValue);
    }

    @Override
    public void refresh(String tokenValue) {
        securityAccessTokenAppService.refresh(tokenValue);
    }

}
