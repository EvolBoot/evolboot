package org.evolboot.security.accesstoken.domain;

import org.evolboot.identity.domain.user.UserRegisterService;

/**
 * @author evol

 */
public interface AccessTokenAppService {

    /**
     * 进行身份认证
     *
     * @param accessTokenAuthenticateToken 认证数据
     * @param loginIp
     * @return
     */
    AccessToken authenticate(AccessTokenAuthenticateToken accessTokenAuthenticateToken, String loginIp);

    /**
     * 注册并登录
     *
     * @param request
     * @return
     */
    AccessToken registerAndGetAccessToken(UserRegisterService.Request request);


    /**
     * 注销
     *
     * @param tokenValue Token 值
     */
    void logout(String tokenValue);

    /**
     * 刷新
     *
     * @param tokenValue Token 值
     */
    void refresh(String tokenValue);


}
