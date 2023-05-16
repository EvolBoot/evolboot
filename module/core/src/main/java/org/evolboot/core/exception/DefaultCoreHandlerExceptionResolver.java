package org.evolboot.core.exception;

import cn.hutool.crypto.CryptoException;
import com.fasterxml.jackson.databind.JsonMappingException;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.CoreI18nMessage;
import org.evolboot.core.i18n.I18NMessageHolder;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.core.util.Assert;
import org.evolboot.core.util.JsonUtil;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.BeanCreationNotAllowedException;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.lang.Nullable;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.util.MimeTypeUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.method.annotation.MethodArgumentConversionNotSupportedException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.evolboot.core.Constant.ErrorStatusCode.ERROR_CODE;

/**
 * 用来拦截错误信息的异常拦截器
 *
 * @author evol
 */
@Slf4j
public abstract class DefaultCoreHandlerExceptionResolver extends org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver {


    public DefaultCoreHandlerExceptionResolver() {
        super();
    }

    @Override
    public ModelAndView resolveException(@Nullable HttpServletRequest request, @Nullable HttpServletResponse response, Object handler, @Nullable Exception ex) {
        try {
            if (ex instanceof HttpNetException) {

                return handleHttpNetException((HttpNetException) ex, request, response, handler);

            } else if (ex instanceof NullPointerException) {

                return handle((NullPointerException) ex, request, response, handler);

            } else if (ex instanceof ErrorCodeException) {

                return handle((ErrorCodeException) ex, request, response, handler);

            } else if (ex instanceof ExtendRuntimeException) {

                return handle((ExtendRuntimeException) ex, request, response, handler);

            } else if (ex instanceof IllegalArgumentException) {

                return handleIllegalArgumentException((IllegalArgumentException) ex, request, response, handler);

            } else if (ex instanceof NoHandlerFoundException) {

                return handleMyNoHandlerFoundException((NoHandlerFoundException) ex, request, response, handler);

            } else if (ex instanceof HttpRequestMethodNotSupportedException) {

                return handleHttpRequestMethodNotSupported((HttpRequestMethodNotSupportedException) ex, request, response, handler);

            } else if (ex instanceof HttpMediaTypeNotSupportedException) {

                return handleHttpMediaTypeNotSupported((HttpMediaTypeNotSupportedException) ex, request, response, handler);

            } else if (ex instanceof HttpMediaTypeNotAcceptableException) {

                return handleHttpMediaTypeNotAcceptable((HttpMediaTypeNotAcceptableException) ex, request, response, handler);

            } else if (ex instanceof MissingPathVariableException) {

                return handleMissingPathVariable((MissingPathVariableException) ex, request, response, handler);

            } else if (ex instanceof MissingServletRequestParameterException) {

                return handleMissingServletRequestParameter((MissingServletRequestParameterException) ex, request, response, handler);

            } else if (ex instanceof ServletRequestBindingException) {

                return handleServletRequestBindingException((ServletRequestBindingException) ex, request, response, handler);

            } else if (ex instanceof ConversionNotSupportedException) {

                return handleConversionNotSupported((ConversionNotSupportedException) ex, request, response, handler);

            } else if (ex instanceof MethodArgumentTypeMismatchException) {

                return handleMethodArgumentTypeMismatchException((MethodArgumentTypeMismatchException) ex, request, response, handler);

            } else if (ex instanceof TypeMismatchException) {

                return handleTypeMismatch((TypeMismatchException) ex, request, response, handler);

            } else if (ex instanceof HttpMessageNotReadableException) {

                return handleHttpMessageNotReadable((HttpMessageNotReadableException) ex, request, response, handler);

            } else if (ex instanceof HttpMessageNotWritableException) {

                return handleHttpMessageNotWritable((HttpMessageNotWritableException) ex, request, response, handler);

            } else if (ex instanceof MethodArgumentNotValidException) {

                return handleMethodArgumentNotValidException((MethodArgumentNotValidException) ex, request, response, handler);

            } else if (ex instanceof MissingServletRequestPartException) {

                return handleMissingServletRequestPartException((MissingServletRequestPartException) ex, request, response, handler);

            } else if (ex instanceof BindException) {

                return handleBindException((BindException) ex, request, response, handler);

            } else if (ex instanceof InvalidDataAccessApiUsageException) {

                return handleInvalidDataAccessApiUsageException((InvalidDataAccessApiUsageException) ex, request, response, handler);

            } else if (ex instanceof ApplicationException) {

                return handleApplicationException((ApplicationException) ex, request, response, handler);

            } else if (ex instanceof BeanCreationNotAllowedException) {

                return handleBeanCreationNotAllowedException((BeanCreationNotAllowedException) ex, request, response, handler);

            } else if (ex instanceof IOException && ex.getClass().getSimpleName().equals("ClientAbortException")) {

                return handleClientAbortException((IOException) ex, request, response, handler);

            } else if (ex instanceof ObjectOptimisticLockingFailureException) {

                return handleObjectOptimisticLockingFailureException((ObjectOptimisticLockingFailureException) ex, request, response, handler);

            } else if (ex instanceof DataIntegrityViolationException) {

                return handleDataIntegrityViolationException((DataIntegrityViolationException) ex, request, response, handler);

            } else if (ex instanceof CryptoException) {

                return handle((CryptoException) ex, request, response, handler);

            } else if (ex instanceof JsonMappingException) {

                return handle((JsonMappingException) ex, request, response, handler);

            } else if (ex instanceof ConversionFailedException) {

                return handle((ConversionFailedException) ex, request, response, handler);

            } else {
                if (log.isDebugEnabled()) {
                    log.error("Handling of [" + ex.getClass().getName() + "] resulted in Exception", ex);
                }
                return handleException(ex, request, response, handler);
            }
        } catch (Exception handlerException) {
            log.error("Handling of [" + ex.getClass().getName() + "] resulted in Exception", handlerException);
        }
        return null;
    }

    protected ModelAndView handleClientAbortException(IOException ex, HttpServletRequest request, HttpServletResponse response, Object handler) {
        ModelAndView modelAndView = new ModelAndView();
        if (log.isDebugEnabled()) {
            log.debug("ClientAbortException , url:" + request.getRequestURI());
        }
        return modelAndView;
    }

    @Override
    protected ModelAndView handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex, HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        return super.handleHttpMediaTypeNotAcceptable(ex, request, response, handler);
    }

    /**
     * 处理网络连不上
     *
     * @param ex
     * @param request
     * @param response
     * @param handler
     * @return
     * @
     */
    protected ModelAndView handleHttpNetException(HttpNetException ex, HttpServletRequest request, HttpServletResponse response, Object handler) {
        ModelAndView modelAndView = new ModelAndView();
        handlerJsonMessage(response, ex.getLocalizedMessage(), ex);
        return modelAndView;
    }


    protected ModelAndView handleException(Exception ex, HttpServletRequest request, HttpServletResponse response, Object handler) {
        ModelAndView modelAndView = new ModelAndView();
        response.setStatus(HttpServletResponse.SC_OK);
        handlerJsonMessage(response, ex.getLocalizedMessage(), ex);
        return modelAndView;
    }


    protected ModelAndView handleIllegalArgumentException(IllegalArgumentException ex, HttpServletRequest request, HttpServletResponse response, Object handler) {
        ModelAndView modelAndView = new ModelAndView();
        response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
        outputStreamWrite(response, ResponseModel.error(ex.getLocalizedMessage()), ex);
        return modelAndView;
    }

    protected ModelAndView handleMyNoHandlerFoundException(NoHandlerFoundException ex, HttpServletRequest request, HttpServletResponse response, Object handler) {
        ModelAndView modelAndView = new ModelAndView();
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        handlerJsonMessage(response, ex.getLocalizedMessage(), ex);
        return modelAndView;
    }


    /**
     * 404
     *
     * @param ex
     * @param request
     * @param response
     * @param handler
     * @return
     * @
     */
    @Override
    protected ModelAndView handleNoHandlerFoundException(NoHandlerFoundException ex, HttpServletRequest request, HttpServletResponse response, Object handler) {
        ModelAndView modelAndView = new ModelAndView();
        pageNotFoundLogger.warn(ex.getMessage());
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        handlerJsonMessage(response, ex.getLocalizedMessage(), ex);
        return modelAndView;
    }

    @Override
    protected ModelAndView handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpServletRequest request, HttpServletResponse response, Object handler) {
        ModelAndView modelAndView = new ModelAndView();
        handlerJsonMessage(response, CoreI18nMessage.parameterTypeError(), ex);
        return modelAndView;
    }

    protected ModelAndView handleObjectOptimisticLockingFailureException(ObjectOptimisticLockingFailureException ex, HttpServletRequest request, HttpServletResponse response, Object handler) {
        ModelAndView modelAndView = new ModelAndView();
        handlerJsonMessage(response, CoreI18nMessage.objectOptimisticLockingFailureException(), ex);
        return modelAndView;
    }


    protected ModelAndView handleDataIntegrityViolationException(DataIntegrityViolationException ex, HttpServletRequest request, HttpServletResponse response, Object handler) {
        ModelAndView modelAndView = new ModelAndView();
        handlerJsonMessage(response, CoreI18nMessage.dataIntegrityViolationException(), ex);
        return modelAndView;
    }

    /**
     * 405
     *
     * @param ex
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws IOException
     */
    @Override
    protected ModelAndView handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpServletRequest request, HttpServletResponse response, Object handler) {
        pageNotFoundLogger.warn(ex.getMessage());
        String[] supportedMethods = ex.getSupportedMethods();
        if (supportedMethods != null) {
            response.setHeader("Allow", StringUtils.arrayToDelimitedString(supportedMethods, ", "));
        }
        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        ModelAndView modelAndView = new ModelAndView();
        handlerJsonMessage(response, ex.getLocalizedMessage(), ex);
        return modelAndView;
    }

    /**
     * Handle the case when a required parameter is missing.
     * <p>The default implementation sends an HTTP 400 error, and returns an empty {@code ModelAndView}.
     * Alternatively, a fallback view could be chosen, or the MissingServletRequestParameterException
     * could be rethrown as-is.
     *
     * @param ex       the MissingServletRequestParameterException to be handled
     * @param request  current HTTP request
     * @param response current HTTP response
     * @param handler  the executed handler
     * @return an empty ModelAndView indicating the exception was handled
     * @throws IOException potentially thrown from response.sendError()
     */
    @Override
    protected ModelAndView handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpServletRequest request, HttpServletResponse response, Object handler) {
        ModelAndView modelAndView = new ModelAndView();
        pageNotFoundLogger.warn(ex.getMessage());
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        handlerJsonMessage(response, ex.getLocalizedMessage(), ex);
        return modelAndView;
    }


    protected void handlerJsonMessage(HttpServletResponse response, String msg, Exception ex) {
        response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
        outputStreamWrite(response, error(ResponseModel.ERROR_CODE, msg), ex);

    }

    protected void handlerJsonMessage(HttpServletResponse response, Integer code, String msg, Exception ex) {
        response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
        outputStreamWrite(response, error(code, msg), ex);
    }

    protected ModelAndView handleInvalidDataAccessApiUsageException(InvalidDataAccessApiUsageException ex, HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        handlerJsonMessage(response, ex.getCause().getMessage(), ex);
        return modelAndView;
    }


    protected ModelAndView handleBeanCreationNotAllowedException(BeanCreationNotAllowedException ex, HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        handlerJsonMessage(response, ex.getCause().getMessage(), ex);
        return modelAndView;
    }


    @Override
    protected ModelAndView handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        BindingResult result = ex.getBindingResult();
        final List<FieldError> fieldErrors = result.getFieldErrors();

        /*
        // 多字段提示
        StringBuilder builder = new StringBuilder();
        for (FieldError error : fieldErrors) {
            builder.append(MessageHolder.message(error.getDefaultMessage(),error.getDefaultMessage(), error.getField()) +"\n");
        }
        */
        FieldError error = fieldErrors.get(0);
        String msg = I18NMessageHolder.message(error.getDefaultMessage());
        ModelAndView modelAndView = new ModelAndView();
        handlerJsonMessage(response, msg, ex);
        return modelAndView;
    }

    /**
     * 类型参数不用匹配
     *
     * @param response
     * @param handler
     * @return
     * @throws IOException
     */
    public ModelAndView handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
//        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        handlerJsonMessage(response, ex.getLocalizedMessage(), ex);
        return new ModelAndView();
    }

    /**
     * 找不到对应此参数的方法
     *
     * @param response
     * @param handler
     * @return
     * @throws IOException
     */
    public ModelAndView handleMethodArgumentConversionNotSupportedException(MethodArgumentConversionNotSupportedException ex, HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        handlerJsonMessage(response, ex.getLocalizedMessage(), ex);
        return new ModelAndView();
    }


    /**
     * 领域错误
     *
     * @param response
     * @param handler
     * @return
     * @throws IOException
     */
    public ModelAndView handleDomainException(DomainException ex, HttpServletRequest request, HttpServletResponse response, Object handler) {
        handlerJsonMessage(response, ex.getMessage(), ex);
        return new ModelAndView();
    }


    /**
     * 空指针
     *
     * @param response
     * @param handler
     * @return
     * @throws IOException
     */
    public ModelAndView handle(NullPointerException ex, HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        log.error("空指针", ex);
        response.setStatus(HttpServletResponse.SC_OK);
        handlerJsonMessage(response, "NullPointerException Exception:" + ex.getLocalizedMessage(), ex);
        return new ModelAndView();
    }

    /**
     * 空指针
     *
     * @param response
     * @param handler
     * @return
     * @throws IOException
     */
    public ModelAndView handle(ConversionFailedException ex, HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        log.error("字段不匹配", ex);
        ModelAndView modelAndView = new ModelAndView();
        handlerJsonMessage(response, ERROR_CODE, "The field type does not match", ex);
        return new ModelAndView();
    }


    protected ModelAndView handle(CryptoException ex, HttpServletRequest request, HttpServletResponse response, Object handler) {
        ModelAndView modelAndView = new ModelAndView();
        handlerJsonMessage(response, ERROR_CODE, "crypto invalid", ex);
        return modelAndView;
    }

    protected ModelAndView handle(JsonMappingException ex, HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.error("Json 异常", ex);
        ModelAndView modelAndView = new ModelAndView();
        handlerJsonMessage(response, ERROR_CODE, "JsonMapping Exception", ex);
        return modelAndView;
    }

    protected ModelAndView handle(ExtendRuntimeException ex, HttpServletRequest request, HttpServletResponse response, Object handler) {
        ModelAndView modelAndView = new ModelAndView();
        handlerJsonMessage(response, ERROR_CODE, ex.getMessage(), ex);
        return modelAndView;
    }

    protected ModelAndView handle(ErrorCodeException ex, HttpServletRequest request, HttpServletResponse response, Object handler) {
        ModelAndView modelAndView = new ModelAndView();
        handlerJsonMessage(response, ex.getErrorCode(), ex.getMessage(), ex);
        return modelAndView;
    }

    /**
     * 应用错误
     *
     * @param response
     * @param handler
     * @return
     */
    protected ModelAndView handleApplicationException(ApplicationException ex, HttpServletRequest request, HttpServletResponse response, Object handler) {
        ModelAndView modelAndView = new ModelAndView();
        response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
        outputStreamWrite(response, error(ex.getCode(), ex.getLocalizedMessage()), ex);
        return modelAndView;
    }

    protected void outputStreamWrite(HttpServletResponse response, Object data, Exception ex) {
        String msgText = JsonUtil.stringify(data);
        try {
            if (msgText == null || Assert.isBlank(msgText.trim())) {
                log.error("异常,但返回信息为空时", ex);
            }
            OutputStream outputStream = response.getOutputStream();
//            outputStream.write(msgText);
            outputStream.write(msgText.getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            log.error("ERROR:", e);
        }
    }

    protected ResponseModel<String> error(Integer errCode, String message) {
        return ResponseModel.error(errCode, message);
    }


}
