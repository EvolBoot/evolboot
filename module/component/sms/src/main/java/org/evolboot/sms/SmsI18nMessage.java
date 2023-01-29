package org.evolboot.sms;

import org.evolboot.core.i18n.MessageHolder;

/**
 * @author evol
 */
public abstract class SmsI18nMessage {

    public static final String NAMESPACE = "sms";

    public static final String MOBILE_NOT_LANK = NAMESPACE + ".mobileNotLank";
    public static final String REGEX_MOBILE_ERROR = NAMESPACE + ".regexMobileError";
    public static final String SEND_ERROR = NAMESPACE + ".sendError";

    public static String mobileNotLank() {
        return MessageHolder.message(MOBILE_NOT_LANK);
    }

    public static String regexMobileError() {
        return MessageHolder.message(REGEX_MOBILE_ERROR);
    }

    public static String sendError() {
        return MessageHolder.message(SEND_ERROR);
    }


}
