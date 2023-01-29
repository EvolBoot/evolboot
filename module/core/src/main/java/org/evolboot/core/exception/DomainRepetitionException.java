package org.evolboot.core.exception;

/**
 * @author evol
 */
public class DomainRepetitionException extends DomainException {

    public DomainRepetitionException(String message) {
        super(message);
    }

    public DomainRepetitionException(String message, Exception ex) {
        super(message, ex);
    }
}
