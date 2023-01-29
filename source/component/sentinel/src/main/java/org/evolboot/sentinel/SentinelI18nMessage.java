package org.evolboot.sentinel;


import org.evolboot.core.i18n.MessageHolder;

/**
 * @author evol
 */
public abstract class SentinelI18nMessage {

    public static final String NAMESPACE = "sentinel";

    public static final String FLOW_LIMITING = NAMESPACE + ".flowLimiting";

    public static String flowLimiting() {
        return MessageHolder.message(FLOW_LIMITING, "flow limiting");
    }


}
