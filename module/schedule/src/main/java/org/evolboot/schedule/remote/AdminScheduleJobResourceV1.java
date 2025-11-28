package org.evolboot.schedule.remote;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.evolboot.core.annotation.AdminClient;
import org.evolboot.core.data.Page;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.schedule.domain.quartz.QuartzJobService;
import org.evolboot.schedule.domain.quartz.dto.QuartzJobCreateRequest;
import org.evolboot.schedule.domain.quartz.dto.QuartzJobQueryRequest;
import org.evolboot.schedule.domain.quartz.dto.QuartzJobResponse;
import org.evolboot.schedule.domain.quartz.dto.QuartzJobUpdateRequest;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.evolboot.schedule.ScheduleConstant.SCHEDULE_QUARTZ_ENABLE;

/**
 * Quartz 任务管理 REST API
 * 异常由框架统一处理，无需手动 try-catch
 *
 * @author evol
 */
@RestController
@RequestMapping("/admin/v1/schedule/jobs")
@Tag(name = "定时任务管理", description = "Quartz 定时任务管理")
@AdminClient
@ConditionalOnProperty(name = SCHEDULE_QUARTZ_ENABLE, havingValue = "true")
public class AdminScheduleJobResourceV1 {

    private final QuartzJobService quartzJobService;

    public AdminScheduleJobResourceV1(QuartzJobService quartzJobService) {
        this.quartzJobService = quartzJobService;
    }

    /**
     * 创建定时任务
     */
    @PostMapping
    @Operation(summary = "创建定时任务")
    public ResponseModel<String> createJob(@Valid @RequestBody QuartzJobCreateRequest request) {
        quartzJobService.createJob(request);
        return ResponseModel.ok();
    }

    /**
     * 删除定时任务
     */
    @DeleteMapping("/{jobName}")
    @Operation(summary = "删除定时任务")
    public ResponseModel<String> deleteJob(
            @Parameter(description = "任务名称") @PathVariable String jobName) {
        quartzJobService.deleteJob(jobName);
        return ResponseModel.ok();
    }

    /**
     * 暂停定时任务
     */
    @PutMapping("/{jobName}/pause")
    @Operation(summary = "暂停定时任务")
    public ResponseModel<String> pauseJob(
            @Parameter(description = "任务名称") @PathVariable String jobName) {
        quartzJobService.pauseJob(jobName);
        return ResponseModel.ok();
    }

    /**
     * 恢复定时任务
     */
    @PutMapping("/{jobName}/resume")
    @Operation(summary = "恢复定时任务")
    public ResponseModel<String> resumeJob(
            @Parameter(description = "任务名称") @PathVariable String jobName) {
        quartzJobService.resumeJob(jobName);
        return ResponseModel.ok();
    }

    /**
     * 立即执行任务
     */
    @PostMapping("/{jobName}/trigger")
    @Operation(summary = "立即执行任务")
    public ResponseModel<String> triggerJob(
            @Parameter(description = "任务名称") @PathVariable String jobName) {
        quartzJobService.triggerJob(jobName);
        return ResponseModel.ok();
    }

    /**
     * 更新任务（支持 Cron 和 Simple 两种类型）
     */
    @PutMapping
    @Operation(summary = "更新任务", description = "支持更新 Cron 表达式或 Simple 任务的间隔参数")
    public ResponseModel<String> updateJob(@Valid @RequestBody QuartzJobUpdateRequest request) {
        quartzJobService.updateJob(request);
        return ResponseModel.ok();
    }

    /**
     * 分页查询任务列表
     */
    @GetMapping
    @Operation(summary = "分页查询任务列表")
    public ResponseModel<Page<QuartzJobResponse>> listJobs(
            @Parameter(description = "任务类型Key（模糊匹配）") @RequestParam(required = false) String jobClassKey,
            @Parameter(description = "任务名称（模糊匹配）") @RequestParam(required = false) String jobName,
            @Parameter(description = "触发器状态") @RequestParam(required = false) String triggerState,
            @Parameter(description = "页码") @RequestParam(defaultValue = "1") Integer page,
            @Parameter(description = "每页数量") @RequestParam(defaultValue = "20") Integer limit) {
        QuartzJobQueryRequest request = new QuartzJobQueryRequest();
        request.setJobClassKey(jobClassKey);
        request.setJobName(jobName);
        request.setTriggerState(triggerState);
        request.setPage(page);
        request.setLimit(limit);

        Page<QuartzJobResponse> result = quartzJobService.listJobs(request);
        return ResponseModel.ok(result);
    }

    /**
     * 查询任务详情
     */
    @GetMapping("/{jobName}")
    @Operation(summary = "查询任务详情")
    public ResponseModel<QuartzJobResponse> getJobDetail(
            @Parameter(description = "任务名称") @PathVariable String jobName) {
        QuartzJobResponse response = quartzJobService.getJobDetail(jobName);
        return ResponseModel.ok(response);
    }

    /**
     * 获取所有可用的任务类型
     */
    @GetMapping("/available-jobs")
    @Operation(summary = "获取所有可用的任务类型")
    public ResponseModel<List<String>> listAvailableJobs() {
        List<String> jobNames = quartzJobService.listAvailableJobNames();
        return ResponseModel.ok(jobNames);
    }
}
