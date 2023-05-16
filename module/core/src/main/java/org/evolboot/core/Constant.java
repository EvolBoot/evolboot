package org.evolboot.core;

/**
 * @author evol
 */
public interface Constant {

    interface HandlerExceptionResolverOrder {
        int ORDER_0 = 0;
        int ORDER_1 = 1;
        int ORDER_2 = 2;
    }

    interface ErrorStatusCode {
        int ACCESS_DENIED_CODE = 403;

        int NOT_FOUND_CODE = 404;

        int SUCCESS_CODE = 0;

        int ERROR_CODE = 500;
    }

    String ERROR_PATH = "/error";

}
