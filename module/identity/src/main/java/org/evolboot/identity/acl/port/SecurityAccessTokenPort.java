package org.evolboot.identity.acl.port;

/**
 * @author evol
 */
public interface SecurityAccessTokenPort {

    /**
     * 踢除下线
     *
     * @param userId
     */
    void kickOut(Long userId);

}
