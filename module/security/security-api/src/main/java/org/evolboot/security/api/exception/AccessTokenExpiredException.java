package org.evolboot.security.api.exception;


import org.evolboot.core.exception.ApplicationException;

/**
 * @author evol
 */
public class AccessTokenExpiredException extends ApplicationException {
    public AccessTokenExpiredException(String message) {
        super(message);
    }
}
