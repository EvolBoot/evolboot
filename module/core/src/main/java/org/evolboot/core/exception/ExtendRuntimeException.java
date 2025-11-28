package org.evolboot.core.exception;

/**
 * @author evol
 */
public class ExtendRuntimeException extends RuntimeException {
    public ExtendRuntimeException(String message) {
        super(message, (Throwable) null, false, false);
    }

    public ExtendRuntimeException(String message, Throwable ex) {
        super(message, ex, false, false);
    }

}