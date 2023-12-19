package org.evolboot.core.exception.handler;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.RequestDispatcher;
import org.evolboot.core.Constant;
import org.evolboot.core.remote.ResponseModel;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author evol
 */
@Hidden
@RestController
@RestControllerAdvice
public class RestErrorController implements ErrorController {


    @GetMapping(path = Constant.ERROR_PATH)
    public ResponseModel<?> handleError(HttpServletRequest request, HttpServletResponse response, Throwable exception) {
        //获取statusCode:401,404,500
        Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String msg = "unknown error";
        if (statusCode == null) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
        } else {
            msg = HttpStatus.valueOf(statusCode).getReasonPhrase();
        }
        return ResponseModel.error(statusCode, msg);
    }


}
