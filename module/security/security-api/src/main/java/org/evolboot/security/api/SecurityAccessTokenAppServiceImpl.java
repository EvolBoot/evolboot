package org.evolboot.security.api;

import org.evolboot.security.api.repository.EvolSessionRepository;
import org.springframework.stereotype.Service;

/**
 * @author evol
 */
@Service
public class SecurityAccessTokenAppServiceImpl implements SecurityAccessTokenAppService {

    private final EvolSessionRepository repository;
    private final LoginService loginService;

    public SecurityAccessTokenAppServiceImpl(EvolSessionRepository repository, LoginService loginService) {
        this.repository = repository;
        this.loginService = loginService;
    }


    @Override
    public EvolSession findByToken(String tokenValue) {
        return repository.findByToken(tokenValue);
    }

    @Override
    public void logout(String tokenValue) {
        repository.deleteByToken(tokenValue);
    }

    @Override
    public void kickOut(Long userId) {
        repository.deleteByUserId(userId);
    }

    @Override
    public LoginService.Response login(LoginService.Request request) {
        return loginService.execute(request);
    }

    @Override
    public void refresh(String token) {
        repository.refresh(token);
    }

}
