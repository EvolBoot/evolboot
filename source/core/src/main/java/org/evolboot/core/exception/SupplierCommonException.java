package org.evolboot.core.exception;

import java.util.function.Supplier;


/**
 * @author evol
 * 
 */
public class SupplierCommonException implements Supplier<ApplicationException> {

    private ApplicationException applicationException;

    public SupplierCommonException(ApplicationException applicationException) {
        this.applicationException = applicationException;
    }

    @Override
    public ApplicationException get() {
        return applicationException;
    }
}
