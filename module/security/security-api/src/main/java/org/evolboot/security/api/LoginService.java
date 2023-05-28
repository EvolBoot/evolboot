package org.evolboot.security.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.domain.IdGenerate;
import org.evolboot.security.api.repository.EvolSessionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author evol
 */
@Service
@Slf4j
public class LoginService {

    private final EvolSessionRepository repository;

    public LoginService(EvolSessionRepository repository) {
        this.repository = repository;
    }

    public Response execute(Request request) {
        EvolSession evolSession = repository.findByUserId(request.getUserId());
        if (evolSession == null) {
            evolSession = new EvolSession(request.getUserId());
        }
        String token = IdGenerate.stringId();
        evolSession.getDevices().put(token, new EvolSessionDevice(request.getLoginIp(), token));
        evolSession.setAuthorities(request.getAuthorities());
        repository.save(token, evolSession);
        return new Response(token);

    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Response {
        private String token;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @Builder
    public static class Request {

        private Long userId;

        private String loginIp;

        private Set<String> authorities;

    }

}
