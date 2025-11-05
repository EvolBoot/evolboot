package org.evolboot.schedule.domain.quartz;

/**
 * @author evol
 */
public class QuartzJobConstant {

    public static final String SCHEDULE_QUARTZ_ENABLE = "evolpn.schedule.quartz.enable";

    /**
     * 默认任务组名称，所有 Quartz 任务统一使用此组
     */
    public static final String DEFAULT_JOB_GROUP = "EVOLBOOT_QUARTZ_JOB_GROUP";
}
