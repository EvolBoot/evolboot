package org.evolboot.schedule.domain.quartz.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 更新 Quartz 任务请求
 * cronExpression 和 intervalMs 二选一，用于指定更新为 Cron 或 Simple 任务
 *
 * @author evol
 */
@Setter
@Getter
@Schema(description = "更新 Quartz 任务请求")
public class QuartzJobUpdateRequest {

    @Schema(description = "任务名称", example = "demo-job-1", requiredMode = Schema.RequiredMode.REQUIRED)
    private String jobName;

    @Schema(description = "新的 Cron 表达式（更新为 Cron 任务时使用）", example = "0 0/10 * * * ?")
    private String cronExpression;

    @Schema(description = "间隔时间（毫秒，更新为 Simple 任务时使用）", example = "60000")
    private Long intervalMs;

    @Schema(description = "重复次数（-1 表示无限重复，更新为 Simple 任务时使用）", example = "-1")
    private Integer repeatCount;

    @Schema(description = "任务描述", example = "每10分钟执行一次的演示任务")
    private String description;

    @Schema(description = "任务参数", example = "{\"key\":\"value\"}")
    private String jobData;
}
