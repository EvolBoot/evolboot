package org.evolboot.schedule.domain.quartz;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.core.data.Pageable;
import org.evolboot.core.util.Assert;
import org.evolboot.schedule.ScheduleConstant;
import org.evolboot.schedule.ScheduleVersion;
import org.evolboot.schedule.domain.quartz.dto.QuartzJobCreateRequest;
import org.evolboot.schedule.domain.quartz.dto.QuartzJobQueryRequest;
import org.evolboot.schedule.domain.quartz.dto.QuartzJobResponse;
import org.evolboot.schedule.domain.quartz.dto.QuartzJobUpdateRequest;
import org.evolboot.schedule.exception.ScheduleException;
import org.quartz.Job;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static org.evolboot.schedule.ScheduleConstant.SCHEDULE_QUARTZ_ENABLE;

/**
 * QuartzJob 业务服务
 * 封装业务逻辑，调用 QuartzJobApi
 *
 * @author evol
 */
@Service
@ConditionalOnProperty(name = SCHEDULE_QUARTZ_ENABLE, havingValue = "true")
@Slf4j
public class QuartzJobService {

    private final QuartzJobApi quartzJobApi;

    /**
     * 任务名称 -> 任务类全路径映射
     * Key: 任务名称（通过 getJobName() 获取）
     * Value: 任务类的全路径名
     */
    private final Map<String, String> jobNameToClassMap = new ConcurrentHashMap<>();

    public QuartzJobService(QuartzJobApi quartzJobApi) {
        this.quartzJobApi = quartzJobApi;
    }

    /**
     * 初始化：扫描所有 BaseQuartzJob 子类
     */
    @PostConstruct
    public void init() {
        scanQuartzJobs();
    }

    /**
     * 扫描所有继承自 BaseQuartzJob 的类
     */
    private void scanQuartzJobs() {
        try {
            ClassPathScanningCandidateComponentProvider scanner =
                    new ClassPathScanningCandidateComponentProvider(false);
            scanner.addIncludeFilter(new AssignableTypeFilter(BaseQuartzJob.class));

            // 扫描 schedule 模块下的所有类
            Set<BeanDefinition> candidates = scanner.findCandidateComponents(ScheduleVersion.class.getPackageName());

            for (BeanDefinition beanDefinition : candidates) {
                String className = beanDefinition.getBeanClassName();
                if (className == null) {
                    continue;
                }

                try {
                    Class<?> clazz = Class.forName(className);
                    // 跳过抽象类
                    if (java.lang.reflect.Modifier.isAbstract(clazz.getModifiers())) {
                        continue;
                    }

                    // 实例化并获取任务名称
                    BaseQuartzJob jobInstance = (BaseQuartzJob) clazz.getDeclaredConstructor().newInstance();
                    String jobName = jobInstance.getJobName();

                    // 检查任务名称是否重复
                    if (jobNameToClassMap.containsKey(jobName)) {
                        String existingClass = jobNameToClassMap.get(jobName);
                        throw ScheduleException.ofInvalidJobClass(
                                String.format("Duplicate job name '%s': %s and %s", jobName, existingClass, className)
                        );
                    }

                    jobNameToClassMap.put(jobName, className);
                    log.info("扫描到 Quartz 任务：{} -> {}", jobName, className);
                } catch (Exception e) {
                    log.error("加载任务类失败：{}", className, e);
                }
            }

            log.info("Quartz 任务扫描完成，共找到 {} 个任务类", jobNameToClassMap.size());
        } catch (Exception e) {
            log.error("扫描 Quartz 任务失败", e);
            throw new RuntimeException("Failed to scan Quartz jobs", e);
        }
    }

    /**
     * 获取所有可用的任务名称列表
     *
     * @return 任务名称列表
     */
    public List<String> listAvailableJobNames() {
        return new ArrayList<>(jobNameToClassMap.keySet());
    }

    /**
     * 根据任务名称获取任务类全路径
     *
     * @param jobName 任务名称
     * @return 任务类全路径
     */
    public String getJobClassByName(String jobName) {
        return jobNameToClassMap.get(jobName);
    }

    /**
     * 创建定时任务
     *
     * @param request 创建请求
     */
    public void createJob(QuartzJobCreateRequest request) {
        // 从 jobClassKey 获取任务类全路径
        String jobClassName = getJobClassByName(request.getJobClassKey());
        if (jobClassName == null) {
            throw ScheduleException.ofInvalidJobClass("任务类型不存在：" + request.getJobClassKey());
        }

        // 校验任务类
        Class<? extends Job> jobClass = validateAndGetJobClass(jobClassName);

        // 根据类型创建任务
        if (request.getCronExpression() != null && !request.getCronExpression().isEmpty()) {
            // Cron 任务
            quartzJobApi.createCronJob(
                    request.getJobName(),
                    ScheduleConstant.DEFAULT_JOB_GROUP,
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
                    ScheduleConstant.DEFAULT_JOB_GROUP,
                    jobClass,
                    request.getIntervalMs(),
                    repeatCount,
                    request.getDescription(),
                    request.getJobData()
            );
        } else {
            throw new IllegalArgumentException("必须指定 cronExpression 或 intervalMs");
        }
    }

    /**
     * 删除任务
     *
     * @param jobName 任务名称
     */
    public void deleteJob(String jobName) {
        quartzJobApi.deleteJob(jobName, ScheduleConstant.DEFAULT_JOB_GROUP);
    }

    /**
     * 暂停任务
     *
     * @param jobName 任务名称
     */
    public void pauseJob(String jobName) {
        quartzJobApi.pauseJob(jobName, ScheduleConstant.DEFAULT_JOB_GROUP);
    }

    /**
     * 恢复任务
     *
     * @param jobName 任务名称
     */
    public void resumeJob(String jobName) {
        quartzJobApi.resumeJob(jobName, ScheduleConstant.DEFAULT_JOB_GROUP);
    }

    /**
     * 立即执行任务
     *
     * @param jobName 任务名称
     */
    public void triggerJob(String jobName) {
        quartzJobApi.triggerJob(jobName, ScheduleConstant.DEFAULT_JOB_GROUP);
    }

    /**
     * 更新任务（支持 Cron 和 Simple 两种类型）
     *
     * @param request 更新请求
     */
    public void updateJob(QuartzJobUpdateRequest request) {
        quartzJobApi.updateJob(
                request.getJobName(),
                ScheduleConstant.DEFAULT_JOB_GROUP,
                request.getCronExpression(),
                request.getIntervalMs(),
                request.getRepeatCount(),
                request.getDescription(),
                request.getJobData()
        );
    }

    /**
     * 分页查询任务列表
     *
     * @param request 查询请求
     * @return 分页结果
     */
    public Page<QuartzJobResponse> listJobs(QuartzJobQueryRequest request) {
        // 获取所有任务
        List<QuartzJobResponse> allJobs = quartzJobApi.listAllJobs();

        // 过滤
        List<QuartzJobResponse> filteredJobs = allJobs.stream()
                .filter(job -> matchesFilter(job, request))
                .collect(Collectors.toList());

        // 手动分页
        Pageable pageable = request.toPageable();
        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getLimit(), filteredJobs.size());

        List<QuartzJobResponse> pageData = start < filteredJobs.size()
                ? filteredJobs.subList(start, end)
                : List.of();

        return PageImpl.<QuartzJobResponse>builder()
                .page(pageable.getPage())
                .limit(pageable.getLimit())
                .totalSize(filteredJobs.size())
                .list(pageData)
                .build();
    }

    /**
     * 查询任务详情
     *
     * @param jobName 任务名称
     * @return 任务详情
     */
    public QuartzJobResponse getJobDetail(String jobName) {
        return quartzJobApi.getJobDetail(jobName, ScheduleConstant.DEFAULT_JOB_GROUP);
    }

    /**
     * 过滤任务
     */
    private boolean matchesFilter(QuartzJobResponse job, QuartzJobQueryRequest request) {
        // 过滤任务类型Key（模糊匹配 jobClass）
        if (request.getJobClassKey() != null && !request.getJobClassKey().isEmpty()) {
            if (job.getJobClass() == null || !job.getJobClass().contains(request.getJobClassKey())) {
                return false;
            }
        }

        // 过滤任务名称（模糊匹配）
        if (request.getJobName() != null && !request.getJobName().isEmpty()) {
            if (job.getJobName() == null || !job.getJobName().contains(request.getJobName())) {
                return false;
            }
        }

        // 过滤触发器状态
        if (request.getTriggerState() != null && !request.getTriggerState().isEmpty()) {
            if (!request.getTriggerState().equals(job.getTriggerState())) {
                return false;
            }
        }

        return true;
    }

    /**
     * 校验并获取任务类
     */
    @SuppressWarnings("unchecked")
    private Class<? extends Job> validateAndGetJobClass(String jobClassName) {
        Assert.notBlank(jobClassName, "schedule job class required");
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
