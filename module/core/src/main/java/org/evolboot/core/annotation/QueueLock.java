package org.evolboot.core.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;


/**
 * 禁止重复提交
 * 以 类名 + 方法名 + 参数 为 key , 如果已存在则抛出异常
 * 默认锁定时间为 100 秒, 100秒后自动解锁
 * 可自定义
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface QueueLock {

    /**
     * 默认超时时间
     *
     * @return
     */
    long timeout() default 120L;

    /**
     * 默认单位
     *
     * @return
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;

    /**
     * 任务名称
     *
     * @return
     */
    String taskName() default "";

    String keyPrefix() default "";

    /**
     * 锁名称
     *
     * @return
     */
    String key() default "";

}
