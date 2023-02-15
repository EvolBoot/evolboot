package org.evolboot.core;

import org.evolboot.core.i18n.I18NMessageHolder;

/**
 * @author evol
 */
public abstract class CoreI18nMessage {

    public static final String NAMESPACE = "core";


    public static final String NOT_BLANK = NAMESPACE + ".notBlank";
    public static final String NOT_EMPTY = NAMESPACE + ".notEmpty";
    public static final String NOT_NULL = NAMESPACE + ".notNull";
    public static final String NOT_FOUND_DOMAIN_OBJECT = NAMESPACE + ".notFoundDomainObject";

    public static final String MAX_FILE_SIZE_ERROR = NAMESPACE + ".maxFileSizeError";
    public static final String PARAMETER_TYPE_ERROR = NAMESPACE + ".parameterTypeError";
    public static final String CONFIGURATION_REPEAT_SETTING_EXCEPTION = NAMESPACE + ".configurationRepeatSettingException";
    public static final String OBJECT_OPTIMISTIC_LOCKING_FAILURE_EXCEPTION = NAMESPACE + ".objectOptimisticLockingFailureException";
    public static final String DATA_INTEGRITY_VIOLATION_EXCEPTION = NAMESPACE + ".dataIntegrityViolationException";
    public static final String REPEAT_SUBMIT = NAMESPACE + ".repeatSubmit";


    public static String notBlank(String str) {
        return I18NMessageHolder.message(NOT_BLANK, "", str);
    }

    public static String notEmpty(String field) {
        return I18NMessageHolder.message(NOT_EMPTY, "", field);
    }

    public static String notNull(String field) {

        return I18NMessageHolder.message(NOT_NULL, "", field);
    }

    public static String parameterTypeError() {
        return I18NMessageHolder.message(PARAMETER_TYPE_ERROR, "PARAMETER_TYPE_ERROR");
    }

    public static String notFoundDomainObject(String obj) {
        return I18NMessageHolder.message(NOT_FOUND_DOMAIN_OBJECT, "", obj);
    }

    public static String configurationRepeatSettingException() {
        return I18NMessageHolder.message(CONFIGURATION_REPEAT_SETTING_EXCEPTION, "");
    }

    public static String maxFileSizeError(Object obj) {
        return I18NMessageHolder.message(MAX_FILE_SIZE_ERROR, "", obj);
    }

    public static String objectOptimisticLockingFailureException() {
        return I18NMessageHolder.message(OBJECT_OPTIMISTIC_LOCKING_FAILURE_EXCEPTION, "");
    }

    public static String dataIntegrityViolationException() {
        return I18NMessageHolder.message(DATA_INTEGRITY_VIOLATION_EXCEPTION, "");
    }

    public static String repeatSubmit() {
        return I18NMessageHolder.message(REPEAT_SUBMIT, "");
    }


}
