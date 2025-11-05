package org.evolboot.schedule.domain.quartz.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "任务名称", example = "demo-job", requiredMode = Schema.RequiredMode.REQUIRED)
    private String jobName;

    @Schema(description = "任务组", example = "default", requiredMode = Schema.RequiredMode.REQUIRED)
    private String jobGroup;

    @Schema(description = "任务类全限定名", example = "org.evolboot.schedule.service.DemoJob", requiredMode = Schema.RequiredMode.REQUIRED)
    private String jobClassName;

    @Schema(description = "Cron 表达式（创建 Cron 任务时必填）", example = "0 0/5 * * * ?")
    private String cronExpression;

    @Schema(description = "间隔时间（毫秒，创建简单任务时必填）", example = "60000")
    private Long intervalMs;

    @Schema(description = "重复次数（-1 表示无限重复）", example = "-1")
    private Integer repeatCount;

    @Schema(description = "任务描述", example = "演示任务")
    private String description;

    @Schema(description = "任务参数（JSON 格式）")
    private Map<String, Object> jobData;
}
