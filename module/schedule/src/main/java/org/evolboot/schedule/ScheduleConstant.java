package org.evolboot.schedule;

/**
 * @author evol
 */
public class ScheduleConstant {

    public static final String SCHEDULE_QUARTZ_ENABLE = "evolpn.schedule.quartz.enable";

    /**
     * 默认任务组名称，所有 Quartz 任务统一使用此组
     */
    public static final String DEFAULT_JOB_GROUP = "EVOLBOOT_QUARTZ_JOB_GROUP";

    public static final String QUARTZ_JOB_PARAM_NAME = "job_param";
}
