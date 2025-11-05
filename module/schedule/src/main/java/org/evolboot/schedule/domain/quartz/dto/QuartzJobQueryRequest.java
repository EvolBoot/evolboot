package org.evolboot.schedule.domain.quartz.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.evolboot.core.data.Pageable;

/**
 * Quartz 任务查询请求
 *
 * @author evol
 */
@Setter
@Getter
@Schema(description = "Quartz 任务查询请求")
public class QuartzJobQueryRequest {

    @Schema(description = "任务组（可选，为空则查询所有）", example = "default")
    private String jobGroup;

    @Schema(description = "任务名称（模糊匹配）", example = "demo")
    private String jobName;

    @Schema(description = "触发器状态（NORMAL, PAUSED, BLOCKED, ERROR, NONE）", example = "NORMAL")
    private String triggerState;

    @Schema(description = "页码（从1开始）", example = "1")
    private Integer page = Pageable.DEFAULT_PAGE;

    @Schema(description = "每页数量（1-100）", example = "20")
    private Integer limit = Pageable.DEFAULT_LIMIT;

    public Pageable toPageable() {
        return Pageable.of(page, limit);
    }
}
