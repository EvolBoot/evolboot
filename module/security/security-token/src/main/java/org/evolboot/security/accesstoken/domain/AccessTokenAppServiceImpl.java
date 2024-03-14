package org.evolboot.security.accesstoken.domain;

import org.evolboot.core.event.EventPublisher;
import org.evolboot.identity.domain.user.service.UserRegisterService;
import org.evolboot.security.accesstoken.acl.client.AccessTokenConfigClient;
import org.evolboot.security.accesstoken.domain.authentication.AccessTokenAuthenticationManager;
import org.evolboot.security.accesstoken.domain.authentication.AuthenticationToken;
import org.evolboot.security.accesstoken.domain.authentication.AuthenticationTokenType;
import org.evolboot.security.accesstoken.domain.authentication.googleauthenticator.GoogleAuthenticatorAuthenticationToken;
import org.evolboot.security.accesstoken.domain.authentication.imagecaptcha.ImageCaptchaAuthenticationToken;
import org.evolboot.security.accesstoken.domain.authentication.mobilecaptcha.MobileCaptchaAuthenticationToken;
import org.evolboot.security.accesstoken.domain.authentication.usernamepassword.UsernamePasswordAuthenticationToken;
import org.evolboot.security.api.LoginService;
import org.evolboot.security.api.SecurityAccessTokenAppService;
import org.evolboot.shared.event.sessionuser.UserLoginEvent;
import org.evolboot.shared.lang.UserIdentity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author evol
 */
@Service
public class AccessTokenAppServiceImpl implements AccessTokenAppService {

    private final AccessTokenAuthenticationManager accessTokenAuthenticationManager;
    private final EventPublisher eventPublisher;
    private final AccessTokenConfigClient accessTokenConfigClient;
    private final RegisterUserAndGetAccessToken registerUserAndGetAccessToken;
    private final SecurityAccessTokenAppService securityAccessTokenAppService;

    public AccessTokenAppServiceImpl(AccessTokenAuthenticationManager accessTokenAuthenticationManager, EventPublisher eventPublisher, AccessTokenConfigClient accessTokenConfigClient, RegisterUserAndGetAccessToken registerUserAndGetAccessToken, SecurityAccessTokenAppService securityAccessTokenAppService) {
        this.accessTokenAuthenticationManager = accessTokenAuthenticationManager;
        this.eventPublisher = eventPublisher;
        this.accessTokenConfigClient = accessTokenConfigClient;
        this.registerUserAndGetAccessToken = registerUserAndGetAccessToken;
        this.securityAccessTokenAppService = securityAccessTokenAppService;
    }

    @Override
    @Transactional
    public AccessToken authenticate(AccessTokenAuthenticateToken accessTokenAuthenticateToken) {

        AuthenticationToken token;

        if (AuthenticationTokenType.MOBILE_CAPTCHA.equals(accessTokenAuthenticateToken.getAuthenticationTokenType())) {
            /**
             * 手机验证码登录
             */
            token = new MobileCaptchaAuthenticationToken(
                    accessTokenAuthenticateToken.getMobilePrefix(),
                    accessTokenAuthenticateToken.getUsername(),
                    accessTokenAuthenticateToken.getCaptchaToken(),
                    accessTokenAuthenticateToken.getCaptchaCode(),
                    accessTokenAuthenticateToken.getDeviceType(),
                    accessTokenAuthenticateToken.getIp()
            );

        } else if (AuthenticationTokenType.GOOGLE_AUTHENTICATOR.equals(accessTokenAuthenticateToken.getAuthenticationTokenType())) {
            /**
             * 谷歌验证码登录
             */
            token = new GoogleAuthenticatorAuthenticationToken(
                    accessTokenAuthenticateToken.getUsername(), accessTokenAuthenticateToken.getPassword(), accessTokenAuthenticateToken.getGoogleAuthenticatorCode()
            );

        } else if (AuthenticationTokenType.USERNAME_EMAIL_MOBILE.equals(accessTokenAuthenticateToken.getAuthenticationTokenType())) {
            /**
             * 普通账户密码登录
             */
            token = new UsernamePasswordAuthenticationToken(
                    accessTokenAuthenticateToken.getUsername(), accessTokenAuthenticateToken.getPassword()
            );

        } else {
            /**
             * 图片校验码+账号密码登录
             */
            token = new ImageCaptchaAuthenticationToken(
                    accessTokenAuthenticateToken.getUsername(),
                    accessTokenAuthenticateToken.getPassword(),
                    accessTokenAuthenticateToken.getCaptchaToken(),
                    accessTokenAuthenticateToken.getCaptchaCode(),
                    accessTokenAuthenticateToken.getDeviceType(),
                    accessTokenAuthenticateToken.getIp()
            );
        }
        AccessToken accessToken = accessTokenAuthenticationManager.authenticate(token);
        accessToken.setLoginIp(accessTokenAuthenticateToken.getIp());

//        kickOutUser(accessToken);// 踢除其他用户下线

        LoginService.Response response = securityAccessTokenAppService.login(new LoginService.Request(accessToken.getPrincipalId(), accessToken.getPrincipalName(), accessTokenAuthenticateToken.getIp(), accessToken.getAuthorities()));
        accessToken.setToken(response.getToken());
        loginEvent(accessToken);
        return accessToken;
    }


    private void loginEvent(AccessToken accessToken) {
        eventPublisher.publishEvent(new UserLoginEvent(
                        accessToken.getPrincipalId(),
                        accessToken.getToken(),
                        accessToken.getLoginIp()
                )
        );
    }


    @Override
    @Deprecated
    public AccessToken registerAndGetAccessToken(UserRegisterService.Request request) {
        AccessToken accessToken = registerUserAndGetAccessToken.execute(request);
        LoginService.Response response = securityAccessTokenAppService.login(new LoginService.Request(accessToken.getPrincipalId(), accessToken.getPrincipalName(), request.getRegisterIp(), accessToken.getAuthorities()));
        accessToken.setToken(response.getToken());
        return accessToken;
    }


    private void kickOutUser(AccessToken accessToken) {
        if (!accessTokenConfigClient.enableSingleLogin()) {
            return;
        }
        if (accessToken.getAuthorities().contains(UserIdentity.ROLE_STAFF.name()) || accessToken.getAuthorities().contains(UserIdentity.ROLE_ADMIN.name())) {
            return;
        }
        securityAccessTokenAppService.kickOut(accessToken.getPrincipalId());
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
