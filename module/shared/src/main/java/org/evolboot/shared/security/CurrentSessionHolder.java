package org.evolboot.shared.security;

/**
 * 当前会话用户
 * 较为简单，去除security的依赖
 * @author evol
 */
public class CurrentSessionHolder {

    private static final ThreadLocal<EvolSession> contextHolder = new ThreadLocal<>();


    public static void setContextHolder(EvolSession evolSession) {
        contextHolder.set(evolSession);
    }


    public static void clearContext() {
        contextHolder.remove();
    }


    /**
     * 主体ID
     * @return
     */
    public static Long getPrincipalId() {
        return contextHolder.get().getPrincipalId();
    }


}
