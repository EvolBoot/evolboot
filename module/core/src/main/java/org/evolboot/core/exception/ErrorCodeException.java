package org.evolboot.core.exception;

import org.evolboot.core.exception.ExtendRuntimeException;
import lombok.Getter;

/**
 * @author evol
 * 
 */
@Getter
public class ErrorCodeException extends ExtendRuntimeException {

    private final int errorCode;

    public ErrorCodeException(ErrCodeI18nMsg errCodeI18NMsg) {
        super(errCodeI18NMsg.getMsg());
        this.errorCode = errCodeI18NMsg.getCode();
    }

    public static ErrorCodeException of(ErrCodeI18nMsg errCodeI18NMsg) {
        return new ErrorCodeException(errCodeI18NMsg);
    }
}
