package org.evolboot.core.exception;

import org.evolboot.core.Constant;

/**
 * @author evol
 */
public class ApplicationException extends RuntimeException {
    private static final long serialVersionUID = 3230630383646562969L;
    private final Integer code;

    public ApplicationException(String message) {
        super(message);
        this.code = Constant.ErrorStatusCode.ERROR_CODE;
    }

    public ApplicationException(Integer code) {
        super();
        this.code = code;
    }

    public ApplicationException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

}
