package org.evolboot.core.exception;

import org.springframework.lang.Nullable;

/**
 * @author evol
 */
public abstract class DomainAssert {

    public static void isNotFound(@Nullable Object object, String message) {
        if (object != null) {
            throw new DomainNotFoundException(message);
        }
    }

    public static void isNotFound(boolean expression, String message) {
        if (!expression) {
            throw new DomainNotFoundException(message);
        }
    }
}
