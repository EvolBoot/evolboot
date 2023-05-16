package org.evolboot.core.exception;


/**
 * 领域层的所有自定义异常都派生自 DomainException 超类
 *
 * @author evol
 */
public class DomainException extends ExtendRuntimeException {

    public DomainException(String message) {
        super(message);
    }

    public DomainException(String message, Exception ex) {
        super(message, ex);
    }

}