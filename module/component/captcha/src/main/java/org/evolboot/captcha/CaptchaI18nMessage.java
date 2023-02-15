package org.evolboot.captcha;

import org.evolboot.core.i18n.I18NMessageHolder;

/**
 * @author evol
 */
public abstract class CaptchaI18nMessage {

    public static final String NAMESPACE = "captcha";


    public static abstract class ImageCaptcha {
        public static final String NAMESPACE = CaptchaI18nMessage.NAMESPACE + ".imagecaptcha";

         public static final String CODE_ERROR = NAMESPACE + ".codeError";

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
            return I18NMessageHolder.message(MOBILE_NOT_BLANK);
        }

        public static String codeError() {
            return I18NMessageHolder.message(CODE_ERROR);
        }

        public static String intervalTimeNotYet() {
            return I18NMessageHolder.message(INTERVAL_TIME_NOT_YET, "");
        }


        public static String sendFailure() {
            return I18NMessageHolder.message(SEND_FAILURE, "Send failure");
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
            return I18NMessageHolder.message(NOT_BLANK);
        }

        public static String regexError() {
            return I18NMessageHolder.message(REGEX_ERROR);
        }

        public static String codeError() {
            return I18NMessageHolder.message(CODE_ERROR);
        }

        public static String intervalTimeNotYet() {
            return I18NMessageHolder.message(INTERVAL_TIME_NOT_YET);
        }

        public static String haveAlreadyUsedError() {
            return I18NMessageHolder.message(HAVE_ALREADY_USED_ERROR);
        }

    }


}
