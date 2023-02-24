package org.evolboot.pay.exception;


import org.evolboot.core.exception.DomainException;

/**
 * @author evol
 */

public class PayException extends DomainException {
    public PayException(String message) {
        super(message);
    }
}
