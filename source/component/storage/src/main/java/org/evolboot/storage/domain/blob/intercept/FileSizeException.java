package org.evolboot.storage.domain.blob.intercept;

import org.evolboot.core.exception.DomainException;

/**
 * @author evol
 * 
 */
public class FileSizeException extends DomainException {

    private String sizeMb;

    public FileSizeException(String message) {
        super(message);
    }
}
