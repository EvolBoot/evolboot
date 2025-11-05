package org.evolboot.schedule.remote;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.annotation.AdminClient;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.core.util.Assert;
import org.evolboot.schedule.dto.QuartzJobCreateRequest;
import org.evolboot.schedule.dto.QuartzJobResponse;
import org.evolboot.schedule.dto.QuartzJobUpdateRequest;
import org.evolboot.schedule.domain.quartz.QuartzJobApi;
import org.quartz.Job;
import org.quartz.SchedulerException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.evolboot.schedule.domain.quartz.QuartzJobConstant.SCHEDULE_QUARTZ_ENABLE;

/**
 * Quartz 任务管理 REST API
 *
 * @author evol
 */
@RestController
@RequestMapping("/admin/v1/schedule/jobs")
@Tag(name = "定时任务管理", description = "Quartz 定时任务管理")
@AdminClient
@ConditionalOnProperty(name = SCHEDULE_QUARTZ_ENABLE, havingValue = "true")
@Slf4j
public class AdminScheduleJobResourceV1 {

    private final QuartzJobApi quartzJobApi;

    public AdminScheduleJobResourceV1(QuartzJobApi quartzJobApi) {
        this.quartzJobApi = quartzJobApi;
    }

    /**
     * 创建定时任务
     */
    @PostMapping
    @Operation(summary = "创建定时任务")
    public ResponseModel<?> createJob(@Valid @RequestBody QuartzJobCreateRequest request) {
        try {
            // 校验任务类
            Class<? extends Job> jobClass = validateAndGetJobClass(request.getJobClassName());

            // 根据类型创建任务
            if (request.getCronExpression() != null && !request.getCronExpression().isEmpty()) {
                // Cron 任务
                quartzJobApi.createCronJob(
                        request.getJobName(),
                        request.getJobGroup(),
                        jobClass,
                        request.getCronExpression(),
                        request.getDescription(),
                        request.getJobData()
                );
            } else if (request.getIntervalMs() != null && request.getIntervalMs() > 0) {
                // 简单间隔任务
                int repeatCount = request.getRepeatCount() != null ? request.getRepeatCount() : -1;
                quartzJobApi.createSimpleJob(
                        request.getJobName(),
                        request.getJobGroup(),
                        jobClass,
                        request.getIntervalMs(),
                        repeatCount,
                        request.getDescription(),
                        request.getJobData()
                );
            } else {
                throw new IllegalArgumentException("必须指定 cronExpression 或 intervalMs");
            }

            return ResponseModel.ok();
        } catch (SchedulerException e) {
            log.error("创建任务失败", e);
            throw new RuntimeException("创建任务失败：" + e.getMessage());
        }
    }

    /**
     * 删除定时任务
     */
    @DeleteMapping("/{jobGroup}/{jobName}")
    @Operation(summary = "删除定时任务")
    public ResponseModel<?> deleteJob(
            @Parameter(description = "任务组") @PathVariable String jobGroup,
            @Parameter(description = "任务名称") @PathVariable String jobName) {
        try {
            quartzJobApi.deleteJob(jobName, jobGroup);
            return ResponseModel.ok();
        } catch (SchedulerException e) {
            log.error("删除任务失败", e);
            throw new RuntimeException("删除任务失败：" + e.getMessage());
        }
    }

    /**
     * 暂停定时任务
     */
    @PutMapping("/{jobGroup}/{jobName}/pause")
    @Operation(summary = "暂停定时任务")
    public ResponseModel<?> pauseJob(
            @Parameter(description = "任务组") @PathVariable String jobGroup,
            @Parameter(description = "任务名称") @PathVariable String jobName) {
        try {
            quartzJobApi.pauseJob(jobName, jobGroup);
            return ResponseModel.ok();
        } catch (SchedulerException e) {
            log.error("暂停任务失败", e);
            throw new RuntimeException("暂停任务失败：" + e.getMessage());
        }
    }

    /**
     * 恢复定时任务
     */
    @PutMapping("/{jobGroup}/{jobName}/resume")
    @Operation(summary = "恢复定时任务")
    public ResponseModel<?> resumeJob(
            @Parameter(description = "任务组") @PathVariable String jobGroup,
            @Parameter(description = "任务名称") @PathVariable String jobName) {
        try {
            quartzJobApi.resumeJob(jobName, jobGroup);
            return ResponseModel.ok();
        } catch (SchedulerException e) {
            log.error("恢复任务失败", e);
            throw new RuntimeException("恢复任务失败：" + e.getMessage());
        }
    }

    /**
     * 立即执行任务
     */
    @PostMapping("/{jobGroup}/{jobName}/trigger")
    @Operation(summary = "立即执行任务")
    public ResponseModel<?> triggerJob(
            @Parameter(description = "任务组") @PathVariable String jobGroup,
            @Parameter(description = "任务名称") @PathVariable String jobName) {
        try {
            quartzJobApi.triggerJob(jobName, jobGroup);
            return ResponseModel.ok();
        } catch (SchedulerException e) {
            log.error("触发任务失败", e);
            throw new RuntimeException("触发任务失败：" + e.getMessage());
        }
    }

    /**
     * 更新任务的 Cron 表达式
     */
    @PutMapping("/cron")
    @Operation(summary = "更新任务的 Cron 表达式")
    public ResponseModel<?> updateJobCron(@Valid @RequestBody QuartzJobUpdateRequest request) {
        try {
            quartzJobApi.updateJobCron(
                    request.getJobName(),
                    request.getJobGroup(),
                    request.getCronExpression()
            );
            return ResponseModel.ok();
        } catch (SchedulerException e) {
            log.error("更新任务 Cron 失败", e);
            throw new RuntimeException("更新任务 Cron 失败：" + e.getMessage());
        }
    }

    /**
     * 查询所有任务
     */
    @GetMapping
    @Operation(summary = "查询所有任务")
    public ResponseModel<List<QuartzJobResponse>> listJobs() {
        try {
            List<Map<String, Object>> jobs = quartzJobApi.listAllJobs();
            List<QuartzJobResponse> responses = jobs.stream()
                    .map(this::convertToResponse)
                    .collect(Collectors.toList());
            return ResponseModel.ok(responses);
        } catch (SchedulerException e) {
            log.error("查询任务列表失败", e);
            throw new RuntimeException("查询任务列表失败：" + e.getMessage());
        }
    }

    /**
     * 查询任务详情
     */
    @GetMapping("/{jobGroup}/{jobName}")
    @Operation(summary = "查询任务详情")
    public ResponseModel<QuartzJobResponse> getJobDetail(
            @Parameter(description = "任务组") @PathVariable String jobGroup,
            @Parameter(description = "任务名称") @PathVariable String jobName) {
        try {
            Map<String, Object> jobInfo = quartzJobApi.getJobDetail(jobName, jobGroup);
            QuartzJobResponse response = convertToResponse(jobInfo);
            return ResponseModel.ok(response);
        } catch (SchedulerException e) {
            log.error("查询任务详情失败", e);
            throw new RuntimeException("查询任务详情失败：" + e.getMessage());
        }
    }

    /**
     * 转换为响应对象
     */
    private QuartzJobResponse convertToResponse(Map<String, Object> jobInfo) {
        QuartzJobResponse response = new QuartzJobResponse();
        response.setJobName((String) jobInfo.get("jobName"));
        response.setJobGroup((String) jobInfo.get("jobGroup"));
        response.setDescription((String) jobInfo.get("description"));
        response.setJobClass((String) jobInfo.get("jobClass"));
        response.setTriggerName((String) jobInfo.get("triggerName"));
        response.setTriggerGroup((String) jobInfo.get("triggerGroup"));
        response.setTriggerState((String) jobInfo.get("triggerState"));
        response.setTriggerType((String) jobInfo.get("triggerType"));
        response.setCronExpression((String) jobInfo.get("cronExpression"));
        response.setRepeatInterval((Long) jobInfo.get("repeatInterval"));
        response.setRepeatCount((Integer) jobInfo.get("repeatCount"));
        response.setPreviousFireTime((java.util.Date) jobInfo.get("previousFireTime"));
        response.setNextFireTime((java.util.Date) jobInfo.get("nextFireTime"));
        return response;
    }

    /**
     * 校验并获取任务类
     */
    @SuppressWarnings("unchecked")
    private Class<? extends Job> validateAndGetJobClass(String jobClassName) {
        Assert.notBlank(jobClassName, "schedule.job.class.required");
        try {
            Class<?> clazz = Class.forName(jobClassName);
            if (!Job.class.isAssignableFrom(clazz)) {
                throw new IllegalArgumentException("任务类必须实现 org.quartz.Job 接口");
            }
            return (Class<? extends Job>) clazz;
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("任务类不存在：" + jobClassName);
        }
    }
}
