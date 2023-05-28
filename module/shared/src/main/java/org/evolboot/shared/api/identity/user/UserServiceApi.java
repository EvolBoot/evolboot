package org.evolboot.shared.api.identity.user;

/**
 * @author evol
 */
public interface UserServiceApi {

    /**
     * 用户是否存在
     * @param userId
     * @return
     */
    boolean existsByUserId(Long userId);

}
