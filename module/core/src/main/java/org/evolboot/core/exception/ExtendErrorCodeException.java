package org.evolboot.core.exception;

/**
 * @author evol
 */
public abstract class ExtendErrorCodeException extends ExtendIllegalArgumentException {

    public abstract int getErrorCode();

    public ExtendErrorCodeException(String message) {
        super(message);
    }

    public ExtendErrorCodeException(String message, Throwable ex) {
        super(message, ex);
    }
}
