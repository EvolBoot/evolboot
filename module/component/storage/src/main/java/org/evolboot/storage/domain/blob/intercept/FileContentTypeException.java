package org.evolboot.storage.domain.blob.intercept;

import org.evolboot.core.exception.DomainException;

/**
 * @author evol
 */
public class FileContentTypeException extends DomainException {
    public FileContentTypeException(String message) {
        super(message);
    }
}
