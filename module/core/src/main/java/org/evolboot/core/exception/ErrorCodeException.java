package org.evolboot.core.exception;

import lombok.Getter;

/**
 * @author evol
 * 
 */
@Getter
public class ErrorCodeException extends ExtendRuntimeException {

    private final int errorCode;

    public ErrorCodeException(ErrCodeMsg errCodeMsg) {
        super(errCodeMsg.getMsg());
        this.errorCode = errCodeMsg.getCode();
    }

    public static ErrorCodeException of(ErrCodeMsg errCodeMsg) {
        return new ErrorCodeException(errCodeMsg);
    }
}
