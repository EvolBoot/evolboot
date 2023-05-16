package org.evolboot.im;

/**
 * IM 错误码
 * IM:  11000-11999
 *
 * @author evol
 */
public interface ImErrorCode {

    /**
     * 已经是好友
     */
    int HAVE_BEEN_FRIENDS = 11000;

    /**
     * 已经拉黑对方
     */
    int ALREADY_ON_YOUR_BLACKLIST = 11001;

    /**
     * 已经被对方拉黑
     */
    int HAS_BEEN_BLOCKED_BY_FRIENDS = 11002;


}
