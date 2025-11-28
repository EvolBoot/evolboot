package org.evolboot.schedule.domain.quartz.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * 创建 Quartz 任务请求
 *
 * @author evol
 */
@Setter
@Getter
@Schema(description = "创建 Quartz 任务请求")
public class QuartzJobCreateRequest {

    @NotBlank(message = "任务类型Key不能为空")
    @Schema(description = "任务类型Key（从可用任务列表中选择）", example = "DemoJob", requiredMode = Schema.RequiredMode.REQUIRED)
    private String jobClassKey;

    @NotBlank(message = "任务名称不能为空")
    @Schema(description = "任务名称（唯一标识）", example = "demo-job-1", requiredMode = Schema.RequiredMode.REQUIRED)
    private String jobName;

    @Schema(description = "Cron 表达式（创建 Cron 任务时必填）", example = "0 0/5 * * * ?")
    private String cronExpression;

    @Schema(description = "间隔时间（毫秒，创建简单任务时必填）", example = "60000")
    private Long intervalMs;

    @Schema(description = "重复次数（-1 表示无限重复）", example = "-1")
    private Integer repeatCount;

    @Schema(description = "任务描述", example = "演示任务")
    private String description;

    @Schema(description = "任务参数")
    private String jobData;
}
