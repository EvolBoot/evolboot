package org.evolboot.core.aspect;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.evolboot.core.CoreI18nMessage;
import org.evolboot.core.annotation.NoRepeatSubmit;
import org.evolboot.core.exception.ErrorCode;
import org.evolboot.core.exception.RepeatSubmitException;
import org.evolboot.core.service.RedisClientAppService;
import org.evolboot.core.util.JsonUtil;
import org.evolboot.shared.security.CurrentSessionHolder;
import org.springframework.core.io.InputStreamSource;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * 重复提交拦截器
 *
 * @author evol
 */
@Aspect
@Component
@Slf4j
public class NoRepeatSubmitAspect {

    private final RedisClientAppService redisClientAppService;
    private final static String REDIS_KEY = "NRS::";


    public NoRepeatSubmitAspect(RedisClientAppService redisClientAppService) {
        this.redisClientAppService = redisClientAppService;
    }

    @Around("@annotation(nrs)")
    public Object around(ProceedingJoinPoint pjp, NoRepeatSubmit nrs) throws Throwable {
        String params = JsonUtil.stringify(filter(pjp.getArgs()));
        String crypt = DigestUtils.md5DigestAsHex(params.getBytes());
        String path = pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName();
        String principalId = "";
        if (nrs.useUserId() && CurrentSessionHolder.haveLoggedIn()) {
            principalId = String.valueOf(CurrentSessionHolder.getPrincipalId());
        }
        String key = REDIS_KEY + principalId + path + crypt;
        log.debug("验证是否重复提交:{}", key);
        if (redisClientAppService.setIfAbsent(key, "true", nrs.timeout(), nrs.timeUnit())) {
            Object result = pjp.proceed();
            redisClientAppService.del(key);
            return result;
        } else {
            // 抛出重复异常
            throw ErrorCode.REPEAT_SUBMIT_EXCEPTION;
        }
    }

    /**
     * 过滤掉不可序列化的参数
     * @param args 方法参数
     * @return 过滤后的参数
     */
    private Object[] filter(Object[] args) {
        return Arrays.stream(args)
                .filter(arg -> !(arg instanceof ServletRequest
                        || arg instanceof ServletResponse
                        || arg instanceof InputStreamSource))
                .toArray();
    }
}
