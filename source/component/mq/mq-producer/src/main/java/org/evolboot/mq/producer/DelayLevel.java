package org.evolboot.mq.producer;

import lombok.Getter;

/**
 * @author evol
 */
@Getter
public enum DelayLevel {

    _1s(1),
    _5s(2),
    _10s(3),
    _30s(4),
    _1m(5),
    _2m(6),
    _3m(7),
    _4m(8),
    _5m(9),
    _6m(10),
    _7m(11),
    _8m(12),
    _9m(13),
    _10m(14),
    _20m(15),
    _30m(16),
    _1h(17),
    _2h(18),
    _1d(19),
    _2d(20);

    /**
     * 对应 RocketMQ 中的 broker.conf 配置的:messageDelayLevel 属性
     * 目前值为：messageDelayLevel=1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h 1d 2d 24h 72h
     */
    private int delayLevel;

    DelayLevel(int delayLevel) {
        this.delayLevel = delayLevel;
    }
}
