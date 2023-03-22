package org.evolboot.core.mq;

import lombok.Getter;

/**
 * @author evol
 */
@Getter
public enum DelayLevel {


    _1s(1, 1),
    _5s(2, 5),
    _10s(3, 10),
    _30s(4, 30),
    _1m(5, 60),
    _2m(6, 2 * 60),
    _3m(7, 3 * 60),
    _4m(8, 4 * 60),
    _5m(9, 5 * 60),
    _6m(10, 6 * 60),
    _7m(11, 7 * 60),
    _8m(12, 8 * 60),
    _9m(13, 9 * 60),
    _10m(14, 10 * 60),
    _20m(15, 20 * 60),
    _30m(16, 30 * 60),
    _1h(17, 60 * 60),
    _2h(18, 2 * 60 * 60),
    _1d(19, 24 * 60 * 60);

    /**
     * rocketmq 4.x 版本
     * 对应 RocketMQ 中的 broker.conf 配置的:messageDelayLevel 属性
     * 目前值为：messageDelayLevel=1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h 1d 2d 24h 72h
     */
    private int delayLevel;

    /**
     * 延迟秒数
     * rocketmq 5.0+ 有效
     */
    private long delayTimeSeconds;

    DelayLevel(int delayLevel, long delayTimeSeconds) {
        this.delayLevel = delayLevel;
        this.delayTimeSeconds = delayTimeSeconds;
    }
}
