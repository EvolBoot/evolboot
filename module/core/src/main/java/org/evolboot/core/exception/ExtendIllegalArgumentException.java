package org.evolboot.core.exception;

/**
 * @author evol
 */
public class ExtendIllegalArgumentException extends ExtendRuntimeException {

    public ExtendIllegalArgumentException(String message) {
        super(message);
    }

    public ExtendIllegalArgumentException(String message, Throwable ex) {
        super(message, ex);
    }
}
