package org.evolboot.identity.acl.client;

/**
 * @author evol
 */
public interface SecurityAccessTokenClient {

    /**
     * 踢除下线
     *
     * @param userId
     */
    void kickOut(Long userId);

}
