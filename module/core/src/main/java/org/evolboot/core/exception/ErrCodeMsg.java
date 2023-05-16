package org.evolboot.core.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author evol
 */
@Getter
@Setter
@AllArgsConstructor
public class ErrCodeMsg {
    private int code;
    private String msg;

    public static ErrCodeMsg of(int code, String msg) {
        return new ErrCodeMsg(code, msg);
    }
}
