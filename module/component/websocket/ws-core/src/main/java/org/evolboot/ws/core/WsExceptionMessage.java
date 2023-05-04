package org.evolboot.ws.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author evol
 */
@Getter
@Setter
@AllArgsConstructor
public class WsExceptionMessage {

    public static final String ERROR_ACTION = "error";

    private String action;

    private String msg;

}
