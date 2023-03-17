package org.evolboot.core.exception;

import org.evolboot.core.Constant;
import org.evolboot.core.remote.ResponseModel;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        String msg = "unknown error";
        if (statusCode == null) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
        } else {
            msg = HttpStatus.valueOf(statusCode).getReasonPhrase();
        }
        return ResponseModel.error(statusCode, msg);
    }


}
