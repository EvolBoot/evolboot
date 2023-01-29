package org.evolboot.captcha;

import org.evolboot.core.i18n.MessageHolder;

/**
 * @author evol
 */
public abstract class CaptchaI18nMessage {

    public static final String NAMESPACE = "captcha";


    public static abstract class ImageCaptcha {
        public static final String NAMESPACE = CaptchaI18nMessage.NAMESPACE + ".imagecaptcha";


        public static final String CODE_NOT_BLANK = NAMESPACE + ".codeNotBlank";
        public static final String CODE_ERROR = NAMESPACE + ".codeError";

        public static String codeNotBlank() {
            return MessageHolder.message(CODE_ERROR);
        }

        public static String codeError() {
            return MessageHolder.message(CODE_ERROR);
        }

    }

    public static abstract class MobileCaptcha {

        public static final String NAMESPACE = CaptchaI18nMessage.NAMESPACE + ".mobilecaptcha";

        public static final String MOBILE_NOT_BLANK = NAMESPACE + ".notBlank";
        public static final String REGEX_ERROR = NAMESPACE + ".regexError";
        public static final String CODE_ERROR = NAMESPACE + ".codeError";
        public static final String INTERVAL_TIME_NOT_YET = NAMESPACE + ".intervalTimeNotYet";
        public static final String HAVE_ALREADY_USED_ERROR = NAMESPACE + ".haveAlreadyUsedError";
        public static final String SEND_FAILURE = NAMESPACE + ".sendFailure";

        public static String mobileNotBlank() {
            return MessageHolder.message(MOBILE_NOT_BLANK);
        }

        public static String regexError() {
            return MessageHolder.message(REGEX_ERROR);
        }

        public static String codeError() {
            return MessageHolder.message(CODE_ERROR);
        }

        public static String intervalTimeNotYet() {
            return MessageHolder.message(INTERVAL_TIME_NOT_YET, "");
        }

        public static String haveAlreadyUsedError() {
            return MessageHolder.message(HAVE_ALREADY_USED_ERROR, "");
        }

        public static String sendFailure() {
            return MessageHolder.message(SEND_FAILURE, "Send failure");
        }
    }


    public static abstract class EmailCaptcha {
        public static final String NAMESPACE = CaptchaI18nMessage.NAMESPACE + ".emailcaptcha";

        public static final String NOT_BLANK = NAMESPACE + ".notBlank";
        public static final String REGEX_ERROR = NAMESPACE + ".regexError";
        public static final String CODE_ERROR = NAMESPACE + ".codeError";
        public static final String INTERVAL_TIME_NOT_YET = NAMESPACE + ".intervalTimeNotYet";
        public static final String HAVE_ALREADY_USED_ERROR = NAMESPACE + ".haveAlreadyUsedError";


        public static String notBlank() {
            return MessageHolder.message(NOT_BLANK);
        }

        public static String regexError() {
            return MessageHolder.message(REGEX_ERROR);
        }

        public static String codeError() {
            return MessageHolder.message(CODE_ERROR);
        }

        public static String intervalTimeNotYet() {
            return MessageHolder.message(INTERVAL_TIME_NOT_YET, "");
        }

        public static String haveAlreadyUsedError() {
            return MessageHolder.message(HAVE_ALREADY_USED_ERROR, "");
        }

    }


}
