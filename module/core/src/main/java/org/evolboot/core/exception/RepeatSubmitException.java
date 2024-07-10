package org.evolboot.core.exception;


/**
 * @author evol
 */
public class RepeatSubmitException extends ErrorCodeException {

    public RepeatSubmitException(ErrCodeMsg errCodeMsg) {
        super(errCodeMsg);
    }
}
