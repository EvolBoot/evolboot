package org.evolboot.system.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.evolboot.core.entity.IdGenerate;
import org.evolboot.core.exception.ExtendRuntimeException;
import org.evolboot.core.util.IpUtil;
import org.evolboot.core.util.JsonUtil;
import org.evolboot.security.api.SecurityAccessTokenHolder;
import org.evolboot.system.domain.operationlog.entity.OperationLog;
import org.evolboot.system.domain.operationlog.OperationLogAppService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author evol
 */
@Component
@Aspect
@Slf4j
public class OperationLogAspect {
    private final OperationLogAppService service;

    public OperationLogAspect(OperationLogAppService service) {
        this.service = service;
    }

    @Pointcut("@annotation(org.evolboot.core.annotation.OperationLog)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long requestId = IdGenerate.longId();
        long beginTime = System.currentTimeMillis();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        org.evolboot.core.annotation.OperationLog syslog = method.getAnnotation(org.evolboot.core.annotation.OperationLog.class);
        boolean serializable = syslog.serializable();
        String operation = syslog.value();
        //请求的方法名
        String classMethod = point.getTarget().getClass().getName() + "." + signature.getName();
        //请求的参数
        Object[] args = point.getArgs();
        String params = null;
        try {
            if (serializable) {
                params = JsonUtil.stringify(args);
            } else {
                params = JsonUtil.stringify(filter(args));
            }
        } catch (Exception e) {
            log.info("存在不可序列化的参数:{}", method.getName());
        }

        String httpMethod = request.getMethod();
        // URL
        String requestUrl = request.getRequestURI();
        //设置IP地址
        String ip = IpUtil.getClientIP(request);
        //用ID
        Long userId = 0L;
        if (SecurityAccessTokenHolder.isLogin()) {
            userId = SecurityAccessTokenHolder.getPrincipalId();
        }

        try {
            //执行方法
            log.info("日志记录：请求ID:{}, 请求人:{}, 请求URL: {}, 请求方法:{},类方法: {} 参数:{} ,IP:{} ", requestId, userId, requestUrl, httpMethod, classMethod, params, ip);
            Object result = point.proceed();
            //执行时长(毫秒)
            long endTime = System.currentTimeMillis();
            long time = endTime - beginTime;
            log.debug("日志记录:请求ID:{}, 执行时间:{}, 执行结果:{} ", requestId, time, result);
            //保存日志
            OperationLog operationLog = OperationLog.builder()
                    .id(requestId)
                    .userId(userId)
                    .beginTime(beginTime)
                    .endTime(endTime)
                    .operation(operation)
                    .httpMethod(httpMethod)
                    .classMethod(classMethod)
                    .requestUrl(requestUrl)
                    .params(params)
                    .result(JsonUtil.stringify(result))
                    .time(time)
                    .ip(ip)
                    .state(true)
                    .build();
            service.create(
                    operationLog
            );
            return result;
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            long time = endTime - beginTime;
            boolean state = false;
            if (e instanceof ExtendRuntimeException) {
                state = true;
            }
            //保存日志
            OperationLog operationLog = OperationLog.builder()
                    .id(requestId)
                    .userId(userId)
                    .beginTime(beginTime)
                    .endTime(endTime)
                    .operation(operation)
                    .httpMethod(httpMethod)
                    .classMethod(classMethod)
                    .requestUrl(requestUrl)
                    .params(params)
                    .result(e.getMessage())
                    .time(time)
                    .ip(ip)
                    .state(state)
                    .build();
            service.create(
                    operationLog
            );

            log.error("日志ID:{}:", requestId, e);
            throw e;
        }
    }

    // 排除掉不可序列化的参数
    private Object[] filter(Object[] args) {
        return Arrays.stream(args).filter(arg -> (
                !(
                        arg instanceof HttpServletRequest
                                || arg instanceof HttpServletResponse
                )
        )).toArray();
    }


}
