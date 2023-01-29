package org.evolboot.core.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author evol
 *
 */
@Getter
@Setter
@AllArgsConstructor
public class ErrCodeI18nMsg {
    private int code;
    private String msg;

    public static ErrCodeI18nMsg of(int code, String msg) {
        return new ErrCodeI18nMsg(code, msg);
    }
}
