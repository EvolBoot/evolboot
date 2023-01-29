package org.evolboot.sms.domain;

import org.evolboot.core.exception.DomainException;

/**
 * @author evol
 */
public class SmsSendException extends DomainException {
    public SmsSendException(String message) {
        super(message);
    }
}
