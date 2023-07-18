package org.evolboot.identity;

import org.evolboot.core.i18n.I18NMessageHolder;

/**
 * @author evol
 */
public abstract class IdentityI18nMessage {

    public static final String NAMESPACE = "identity";

    public abstract static class Permission {
        public static final String NAMESPACE = IdentityI18nMessage.NAMESPACE + ".permission";
        public static final String PERM_NOT_EMPTY = NAMESPACE + ".perm.notEmpty";
        public static final String TITLE_NOT_EMPTY = NAMESPACE + ".title.notEmpty";
        public static final String NOT_FOUND = NAMESPACE + ".notFound";
        public static final String ID_NOT_NULL = NAMESPACE + ".id.notNull";


        public static String idNotNull() {
            return I18NMessageHolder.message(ID_NOT_NULL);
        }

        public static String notFound() {
            return I18NMessageHolder.message(NOT_FOUND);
        }

        public static String permNotEmpty() {
            return I18NMessageHolder.message(PERM_NOT_EMPTY);
        }

        public static String titleNotEmpty() {
            return I18NMessageHolder.message(TITLE_NOT_EMPTY);
        }
    }

    public abstract static class Role {
        public static final String NAMESPACE = IdentityI18nMessage.NAMESPACE + ".role";
        public static final String ROLE_NAME_REPETITION_EXCEPTION = NAMESPACE + ".roleNameRepetitionException";
        public static final String ROLE_NOT_FOUND = NAMESPACE + ".roleNotFound";
        public static final String ID_NOT_NULL = NAMESPACE + ".id.notNull";
        public static final String permissionsNotFound = NAMESPACE + ".permissions.notFound";

        public static String roleNameRepetitionException(String roleName) {
            return I18NMessageHolder.message(ROLE_NAME_REPETITION_EXCEPTION, roleName);
        }

        public static String roleNotFound() {
            return I18NMessageHolder.message(ROLE_NOT_FOUND);
        }

        public static String idNotNull() {
            return I18NMessageHolder.message(ID_NOT_NULL);
        }

        public static String permissionsNotFound(String permissions) {
            return I18NMessageHolder.message(permissionsNotFound, permissions);
        }
    }

    public abstract static class User {

        public static final String NAMESPACE = IdentityI18nMessage.NAMESPACE + ".user";

        public static final String USERNAME_NOT_EMPTY = NAMESPACE + ".usernameNotEmpty";
        public static final String MOBILE_NOT_EMPTY = NAMESPACE + ".mobileNotEmpty";
        public static final String EMAIL_NOT_EMPTY = NAMESPACE + ".emailNotEmpty";
        public static final String PASSWORD_NOT_EMPTY = NAMESPACE + ".passwordNotEmpty";
        public static final String USERNAME_ALREADY_EXISTED = NAMESPACE + ".usernameAlreadyExisted";
        public static final String MOBILE_ALREADY_EXISTED = NAMESPACE + ".mobileAlreadyExisted";
        public static final String EMAIL_ALREADY_EXISTED = NAMESPACE + ".emailAlreadyExisted";

        public static final String USERNAME_VALID_FAIL = NAMESPACE + ".usernameValidFail";
        public static final String PASSWORD_VALID_FAIL = NAMESPACE + ".passwordValidFail";
        public static final String PASSWORD_WRONG = NAMESPACE + ".passwordWrong";

        public static final String VERIFY_IMAGE_ERROR = NAMESPACE + ".verifyImageError";
        public static final String VERIFY_PASSWORD_ERROR = NAMESPACE + ".verifyPasswordError";
        public static final String OLD_PASSWORD_ERROR = NAMESPACE + ".oldPasswordError";
        public static final String CONFIRM_PASSWORD_ERROR = NAMESPACE + ".confirmPasswordError";
        public static final String OLD_PASSWORD_AND_NEW_PASSWORD_EQUALS = NAMESPACE + ".oldPasswordAndNewPasswordEquals";
        public static final String IMG_VERIFY_TOKEN_NOT_EMPTY = NAMESPACE + ".imgVerifyTokenNotEmpty";

        public static final String USER_NOT_FOUND = NAMESPACE + ".userNotFound";
        public static final String USER_ID_NOT_NULL = NAMESPACE + ".userIdNotNull";


        public static final String OLD_PASSWORD_NOT_EMPTY = NAMESPACE + ".oldPasswordNotEmpty";
        public static final String NEW_PASSWORD_NOT_EMPTY = NAMESPACE + ".newPasswordNotEmpty";
        public static final String CONFIRM_PASSWORD_NOT_EMPTY = NAMESPACE + ".confirmPasswordNotEmpty";

        public static final String IMG_VERIFY_CODE_NOT_EMPTY = NAMESPACE + ".imgVerifyCodeNotEmpty";
        public static final String ROLES_NOT_FOUND = NAMESPACE + ".roles.notFound";
        public static final String STATE_NOT_ACTIVE = NAMESPACE + ".state.notActive";

        public static final String INVITER_DOES_NOT_EXIST = NAMESPACE + ".inviterDoesNotExist";


        public static String imgVerifyCodeNotEmpty() {
            return I18NMessageHolder.message(IMG_VERIFY_CODE_NOT_EMPTY);
        }


        public static String usernameNotEmpty() {
            return I18NMessageHolder.message(USERNAME_NOT_EMPTY);
        }

        public static String mobileNotEmpty() {
            return I18NMessageHolder.message(MOBILE_NOT_EMPTY);
        }

        public static String emailNotEmpty() {
            return I18NMessageHolder.message(EMAIL_NOT_EMPTY);
        }

        public static String passwordNotEmpty() {
            return I18NMessageHolder.message(PASSWORD_NOT_EMPTY);
        }

        public static String usernameAlreadyExisted() {
            return I18NMessageHolder.message(USERNAME_ALREADY_EXISTED);
        }

        public static String emailAlreadyExisted() {
            return I18NMessageHolder.message(EMAIL_ALREADY_EXISTED);
        }

        public static String mobileAlreadyExisted() {
            return I18NMessageHolder.message(MOBILE_ALREADY_EXISTED);
        }

        public static String usernameValidFail() {
            return I18NMessageHolder.message(USERNAME_VALID_FAIL);
        }

        public static String passwordValidFail() {
            return I18NMessageHolder.message(PASSWORD_VALID_FAIL);
        }


        public static String verifyPasswordError() {
            return I18NMessageHolder.message(VERIFY_PASSWORD_ERROR);
        }

        public static String oldPasswordError() {
            return I18NMessageHolder.message(OLD_PASSWORD_ERROR);
        }

        public static String confirmPasswordError() {
            return I18NMessageHolder.message(CONFIRM_PASSWORD_ERROR);
        }

        public static String oldPasswordAndNewPasswordEquals() {
            return I18NMessageHolder.message(OLD_PASSWORD_AND_NEW_PASSWORD_EQUALS);
        }

        public static String passwordWrong() {
            return I18NMessageHolder.message(PASSWORD_WRONG);
        }


        public static String userNotFound() {
            return I18NMessageHolder.message(USER_NOT_FOUND);
        }


        public static String userIdNotNull() {
            return I18NMessageHolder.message(USER_ID_NOT_NULL);
        }

        public static String rolesNotFound(String roleIds) {
            return I18NMessageHolder.message(ROLES_NOT_FOUND, roleIds);
        }


        public static String oldPasswordNotEmpty() {
            return I18NMessageHolder.message(OLD_PASSWORD_NOT_EMPTY);
        }

        public static String newPasswordNotEmpty() {
            return I18NMessageHolder.message(NEW_PASSWORD_NOT_EMPTY);
        }

        public static String confirmPasswordNotEmpty() {
            return I18NMessageHolder.message(CONFIRM_PASSWORD_NOT_EMPTY);
        }

        public static String verifyImageError() {
            return I18NMessageHolder.message(VERIFY_IMAGE_ERROR);
        }

        public static String imgVerifyTokenNotEmpty() {
            return I18NMessageHolder.message(IMG_VERIFY_TOKEN_NOT_EMPTY);
        }

        public static String stateNotActive() {
            return I18NMessageHolder.message(STATE_NOT_ACTIVE);
        }

        public static String inviterDoesNotExist() {
            return I18NMessageHolder.message(INVITER_DOES_NOT_EXIST);
        }
    }

    public static class UserRole {
        public static final String NAMESPACE = IdentityI18nMessage.NAMESPACE + ".userrole";

        public static final String NOT_FOUND = NAMESPACE + ".notFound";

        public static String notFound() {
            return I18NMessageHolder.message(NOT_FOUND);
        }
    }

    /**
     * UserId
     */
    public static class UserId {
        public static final String NAMESPACE = IdentityI18nMessage.NAMESPACE + ".userid";

        public static final String NOT_FOUND = NAMESPACE + ".notFound";

        // 该用户ID已使用
        public static final String HAS_BEEN_USED = NAMESPACE + ".hasBeenUsed";

        // 请稍等
        public static final String PLEASE_WAIT = NAMESPACE + ".pleaseWait";

        public static String pleaseWait() {
            return I18NMessageHolder.message(PLEASE_WAIT);
        }

        public static String hasBeenUsed() {
            return I18NMessageHolder.message(HAS_BEEN_USED);
        }

        public static String notFound() {
            return I18NMessageHolder.message(NOT_FOUND);
        }
    }

}
