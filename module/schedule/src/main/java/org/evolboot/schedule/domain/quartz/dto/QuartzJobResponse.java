package org.evolboot.schedule.domain.quartz.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Quartz 任务响应
 *
 * @author evol
 */
@Setter
@Getter
@Schema(description = "Quartz 任务响应")
    public class QuartzJobResponse {

    @Schema(description = "任务名称", example = "demo-job")
    private String jobName;

    @Schema(description = "任务组", example = "default")
    private String jobGroup;

    @Schema(description = "任务描述", example = "演示任务")
    private String description;

    @Schema(description = "任务类名", example = "org.evolboot.schedule.service.QuartzJobDemoJob")
    private String jobClass;

    @Schema(description = "触发器名称", example = "demo-job-trigger")
    private String triggerName;

    @Schema(description = "触发器组", example = "default")
    private String triggerGroup;

    @Schema(description = "触发器状态（NORMAL, PAUSED, BLOCKED, ERROR, NONE）", example = "NORMAL")
    private String triggerState;

    @Schema(description = "是否已暂停", example = "false")
    private Boolean paused;

    @Schema(description = "触发器类型", example = "CRON")
    private String triggerType;

    @Schema(description = "Cron 表达式", example = "0 0/5 * * * ?")
    private String cronExpression;

    @Schema(description = "重复间隔（毫秒）", example = "60000")
    private Long repeatInterval;

    @Schema(description = "重复次数", example = "-1")
    private Integer repeatCount;

    @Schema(description = "上次执行时间")
    private Date previousFireTime;

    @Schema(description = "下次执行时间")
    private Date nextFireTime;

    @Schema(description = "任务数据", example = "default")
    private String jobData;
}

