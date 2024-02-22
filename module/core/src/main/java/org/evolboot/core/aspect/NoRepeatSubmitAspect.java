package org.evolboot.core.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.evolboot.core.CoreI18nMessage;
import org.evolboot.core.annotation.NoRepeatSubmit;
import org.evolboot.core.exception.RepeatSubmitException;
import org.evolboot.core.service.RedisClientAppService;
import org.evolboot.core.util.JsonUtil;
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
        String key = REDIS_KEY + path + crypt;
        log.debug("验证是否重复提交:{}", key);
        try {
            if (redisClientAppService.setIfAbsent(key, "true", nrs.timeout(), nrs.timeUnit())) {
                return pjp.proceed();
            } else {
                // 抛出重复异常
                throw new RepeatSubmitException(CoreI18nMessage.repeatSubmit());
            }
        } finally {
            redisClientAppService.del(key);
        }
    }

    // 排除掉不可雪序列化的参数
    private Object[] filter(Object[] args) {
        return Arrays.stream(args).filter(arg -> (
                !(
                        arg instanceof HttpServletRequest
                                || arg instanceof HttpServletResponse
                )
        )).toArray();
    }
}
