package org.evolboot.core.exception;

import org.evolboot.core.exception.ExtendRuntimeException;

/**
 * @author evol
 * 
 */
public class ExtendIllegalArgumentException extends ExtendRuntimeException {

    public ExtendIllegalArgumentException(String message) {
        super(message);
    }

    public ExtendIllegalArgumentException(String message, Exception ex) {
        super(message, ex);
    }
}
