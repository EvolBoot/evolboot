package org.evolboot.sentinel;


import org.evolboot.core.i18n.I18NMessageHolder;

/**
 * @author evol
 */
public abstract class SentinelI18nMessage {

    public static final String NAMESPACE = "sentinel";

    public static final String FLOW_LIMITING = NAMESPACE + ".flowLimiting";

    public static String flowLimiting() {
        return I18NMessageHolder.message(FLOW_LIMITING, "flow limiting");
    }


}
