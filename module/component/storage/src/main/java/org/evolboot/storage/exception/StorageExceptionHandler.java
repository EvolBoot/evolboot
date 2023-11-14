package org.evolboot.storage.exception;

import org.evolboot.core.Constant;
import org.evolboot.core.exception.handler.DefaultCoreHandlerExceptionResolver;
import org.evolboot.storage.StorageI18nMessage;
import org.springframework.boot.autoconfigure.web.servlet.MultipartProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author evol
 */
@Component
public class StorageExceptionHandler extends DefaultCoreHandlerExceptionResolver {

    private final MultipartProperties multipartProperties;

    public StorageExceptionHandler(MultipartProperties multipartProperties) {
        super();
        this.multipartProperties = multipartProperties;
        setOrder(Constant.HandlerExceptionResolverOrder.ORDER_0);
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        if (ex instanceof MaxUploadSizeExceededException) {
            return handleMaxUploadSizeExceededException((MaxUploadSizeExceededException) ex, request, response, handler);
        }
        return null;
    }

    protected ModelAndView handleMaxUploadSizeExceededException(MaxUploadSizeExceededException ex, HttpServletRequest request, HttpServletResponse response, Object handler) {
        ModelAndView modelAndView = new ModelAndView();
        handlerJsonMessage(response, StorageI18nMessage.maxFileSizeError(multipartProperties.getMaxFileSize().toMegabytes() + " MB"), ex);
        return modelAndView;
    }

}
