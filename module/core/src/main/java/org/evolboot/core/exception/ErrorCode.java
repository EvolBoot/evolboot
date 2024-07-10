package org.evolboot.core.exception;


import org.evolboot.core.CoreI18nMessage;

/**
 * Core: 10001-10999
 *
 */
public interface ErrorCode {

    /**
     * 重复提交
     */
    Integer REPEAT_SUBMIT = 10001;
    RepeatSubmitException REPEAT_SUBMIT_EXCEPTION = new RepeatSubmitException(new ErrCodeMsg(REPEAT_SUBMIT, CoreI18nMessage.repeatSubmit()));


}

