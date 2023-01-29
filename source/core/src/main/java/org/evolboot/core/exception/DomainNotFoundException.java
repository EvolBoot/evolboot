package org.evolboot.core.exception;

/**
 * @author evol
 */
public class DomainNotFoundException extends DomainException {

    public DomainNotFoundException(String message) {
        super(message);
    }

    public DomainNotFoundException(String message, Exception ex) {
        super(message, ex);
    }
}
