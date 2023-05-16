package org.evolboot.security.api.repository;

import org.evolboot.security.api.EvolSession;

/**
 * @author evol
 */
public interface EvolSessionRepository {

    String save(String token, EvolSession evolSession);

    EvolSession findByToken(String token);

    EvolSession findByUserId(Long userId);

    void deleteByToken(String token);

    void deleteByUserId(Long userId);

    void refresh(String token);
}
