package org.evolboot.identity;

import org.evolboot.core.i18n.MessageHolder;

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
            return MessageHolder.message(ID_NOT_NULL, "id not null");
        }

        public static String notFound() {
            return MessageHolder.message(NOT_FOUND, "notFound");
        }

        public static String permNotEmpty() {
            return MessageHolder.message(PERM_NOT_EMPTY, "perm not empty");
        }

        public static String titleNotEmpty() {
            return MessageHolder.message(TITLE_NOT_EMPTY, "title not empty");
        }
    }

    public abstract static class Role {
        public static final String NAMESPACE = IdentityI18nMessage.NAMESPACE + ".role";
        public static final String ROLE_NAME_REPETITION_EXCEPTION = NAMESPACE + ".roleNameRepetitionException";
        public static final String ROLE_NOT_FOUND = NAMESPACE + ".roleNotFound";
        public static final String ID_NOT_NULL = NAMESPACE + ".id.notNull";
        public static final String permissionsNotFound = NAMESPACE + ".permissions.notFound";

        public static String roleNameRepetitionException(String roleName) {
            return MessageHolder.message(ROLE_NAME_REPETITION_EXCEPTION, "", roleName);
        }

        public static String roleNotFound() {
            return MessageHolder.message(ROLE_NOT_FOUND);
        }

        public static String idNotNull() {
            return MessageHolder.message(ID_NOT_NULL, "role id not null");
        }

        public static String permissionsNotFound(String permissions) {
            return MessageHolder.message(permissionsNotFound, "", permissions);
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
        public static final String STATUS_NOT_ACTIVE = NAMESPACE + ".status.notActive";

        public static final String INVITER_DOES_NOT_EXIST = NAMESPACE + ".inviterDoesNotExist";


        public static String imgVerifyCodeNotEmpty() {
            return MessageHolder.message(IMG_VERIFY_CODE_NOT_EMPTY, "");
        }


        public static String usernameNotEmpty() {
            return MessageHolder.message(USERNAME_NOT_EMPTY, "");
        }

        public static String mobileNotEmpty() {
            return MessageHolder.message(MOBILE_NOT_EMPTY, "");
        }

        public static String emailNotEmpty() {
            return MessageHolder.message(EMAIL_NOT_EMPTY, "");
        }

        public static String passwordNotEmpty() {
            return MessageHolder.message(PASSWORD_NOT_EMPTY, "");
        }

        public static String usernameAlreadyExisted() {
            return MessageHolder.message(USERNAME_ALREADY_EXISTED, "");
        }

        public static String emailAlreadyExisted() {
            return MessageHolder.message(EMAIL_ALREADY_EXISTED, "");
        }

        public static String mobileAlreadyExisted() {
            return MessageHolder.message(MOBILE_ALREADY_EXISTED, "");
        }

        public static String usernameValidFail() {
            return MessageHolder.message(USERNAME_VALID_FAIL, "");
        }

        public static String passwordValidFail() {
            return MessageHolder.message(PASSWORD_VALID_FAIL, "");
        }


        public static String verifyPasswordError() {
            return MessageHolder.message(VERIFY_PASSWORD_ERROR, "");
        }

        public static String oldPasswordError() {
            return MessageHolder.message(OLD_PASSWORD_ERROR, "");
        }

        public static String confirmPasswordError() {
            return MessageHolder.message(CONFIRM_PASSWORD_ERROR, "");
        }

        public static String oldPasswordAndNewPasswordEquals() {
            return MessageHolder.message(OLD_PASSWORD_AND_NEW_PASSWORD_EQUALS, "");
        }

        public static String passwordWrong() {
            return MessageHolder.message(PASSWORD_WRONG, "");
        }


        public static String userNotFound() {
            return MessageHolder.message(USER_NOT_FOUND, "");
        }


        public static String userIdNotNull() {
            return MessageHolder.message(USER_ID_NOT_NULL, "");
        }

        public static String rolesNotFound(String roleIds) {
            return MessageHolder.message(ROLES_NOT_FOUND, "", roleIds);
        }


        public static String oldPasswordNotEmpty() {
            return MessageHolder.message(OLD_PASSWORD_NOT_EMPTY, "");
        }

        public static String newPasswordNotEmpty() {
            return MessageHolder.message(NEW_PASSWORD_NOT_EMPTY, "");
        }

        public static String confirmPasswordNotEmpty() {
            return MessageHolder.message(CONFIRM_PASSWORD_NOT_EMPTY, "");
        }

        public static String verifyImageError() {
            return MessageHolder.message(VERIFY_IMAGE_ERROR, "");
        }

        public static String imgVerifyTokenNotEmpty() {
            return MessageHolder.message(IMG_VERIFY_TOKEN_NOT_EMPTY, "");
        }

        public static String statusNotActive() {
            return MessageHolder.message(STATUS_NOT_ACTIVE, "");
        }

        public static String inviterDoesNotExist() {
            return MessageHolder.message(INVITER_DOES_NOT_EXIST, "Inviter does not exist");
        }
    }

    public static class UserRole {
        public static final String NAMESPACE = IdentityI18nMessage.NAMESPACE + ".userrole";

        public static final String NOT_FOUND = NAMESPACE + ".notFound";

        public static String notFound() {
            return MessageHolder.message(NOT_FOUND, "not Found");
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
            return MessageHolder.message(PLEASE_WAIT, "Please Wait");
        }

        public static String hasBeenUsed() {
            return MessageHolder.message(HAS_BEEN_USED, "UserId has been Used");
        }

        public static String notFound() {
            return MessageHolder.message(NOT_FOUND, "UserId not Found");
        }
    }

}
