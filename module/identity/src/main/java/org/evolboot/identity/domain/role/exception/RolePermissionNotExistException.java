package org.evolboot.identity.domain.role.exception;


import org.evolboot.core.exception.DomainException;

/**
 * @author evol
 */
public class RolePermissionNotExistException extends DomainException {
    public RolePermissionNotExistException(String message) {
        super(message);
    }
}
