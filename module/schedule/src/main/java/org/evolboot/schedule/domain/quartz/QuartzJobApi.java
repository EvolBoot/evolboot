package org.evolboot.schedule.domain.quartz;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.util.Assert;
import org.evolboot.schedule.ScheduleConstant;
import org.evolboot.schedule.domain.quartz.dto.QuartzJobResponse;
import org.evolboot.schedule.exception.ScheduleException;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.evolboot.schedule.ScheduleConstant.SCHEDULE_QUARTZ_ENABLE;

/**
 * Quartz 任务管理 API
 * 提供任务的增删改查、暂停、恢复等操作
 * 异常统一转换为 ScheduleException
 *
 * @author evol
 */
@Service
@ConditionalOnProperty(name = SCHEDULE_QUARTZ_ENABLE, havingValue = "true")
@Slf4j
public class QuartzJobApi {

    private final Scheduler scheduler;

    public QuartzJobApi(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    /**
     * 创建 Cron 任务
     *
     * @param jobName        任务名称
     * @param jobGroup       任务组
     * @param jobClass       任务类
     * @param cronExpression Cron 表达式
     * @param description    任务描述
     * @param jobData        任务参数
     */
    public void createCronJob(String jobName, String jobGroup, Class<? extends Job> jobClass,
                              String cronExpression, String description, String jobData) {
        Assert.notBlank(jobName, "schedule.job.name.required");
        Assert.notBlank(jobGroup, "schedule.job.group.required");
        Assert.notNull(jobClass, "schedule.job.class.required");
        Assert.notBlank(cronExpression, "schedule.job.cron.required");

        try {
            // 检查任务是否已存在
            JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
            if (scheduler.checkExists(jobKey)) {
                throw ScheduleException.ofJobAlreadyExists(jobName, jobGroup);
            }

            // 创建 JobDetail
            JobDataMap dataMap = new JobDataMap();
            if (jobData != null && !jobData.isEmpty()) {
                dataMap.put(ScheduleConstant.QUARTZ_JOB_PARAM_NAME, jobData);
            }

            JobDetail jobDetail = JobBuilder.newJob(jobClass)
                    .withIdentity(jobKey)
                    .withDescription(description)
                    .usingJobData(dataMap)
                    .storeDurably(true)
                    .build();

            // 创建 Cron Trigger
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression)
                    .withMisfireHandlingInstructionDoNothing();

            CronTrigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(jobName + "-trigger", jobGroup)
                    .withDescription(description)
                    .withSchedule(scheduleBuilder)
                    .build();

            // 调度任务
            scheduler.scheduleJob(jobDetail, trigger);
            log.info("创建 Quartz 任务成功：{}.{}，Cron={}", jobGroup, jobName, cronExpression);
        } catch (SchedulerException e) {
            log.error("创建 Quartz 任务失败：{}.{}", jobGroup, jobName, e);
            throw ScheduleException.ofJobCreateFailed(jobGroup + "." + jobName, e);
        }
    }

    /**
     * 创建简单间隔任务（Fixed Rate 模式 + @DisallowConcurrentExecution）
     * 任务按固定间隔触发，配合 @DisallowConcurrentExecution 防止并发执行
     *
     * @param jobName     任务名称
     * @param jobGroup    任务组
     * @param jobClass    任务类
     * @param intervalMs  间隔时间（毫秒）
     * @param repeatCount 重复次数（-1 表示无限重复）
     * @param description 任务描述
     * @param jobData     任务参数
     */
    public void createSimpleJob(String jobName, String jobGroup, Class<? extends Job> jobClass,
                                long intervalMs, int repeatCount, String description, String jobData) {
        Assert.notBlank(jobName, "schedule.job.name.required");
        Assert.notBlank(jobGroup, "schedule.job.group.required");
        Assert.notNull(jobClass, "schedule.job.class.required");

        try {
            JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
            if (scheduler.checkExists(jobKey)) {
                throw ScheduleException.ofJobAlreadyExists(jobName, jobGroup);
            }

            // 准备 JobDataMap
            JobDataMap dataMap = new JobDataMap();
            if (jobData != null && !jobData.isEmpty()) {
                dataMap.put(ScheduleConstant.QUARTZ_JOB_PARAM_NAME, jobData);
            }

            JobDetail jobDetail = JobBuilder.newJob(jobClass)
                    .withIdentity(jobKey)
                    .withDescription(description)
                    .usingJobData(dataMap)
                    .storeDurably(true) // 确保 JobDetail 持久化，即使 Trigger 删除也不丢失
                    .build();

            // 使用 SimpleTrigger 的 repeatForever() 或 withRepeatCount()
            SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                    .withIntervalInMilliseconds(intervalMs)
                    .withMisfireHandlingInstructionNextWithExistingCount(); // Misfire 策略

            if (repeatCount == -1) {
                scheduleBuilder.repeatForever();
            } else {
                scheduleBuilder.withRepeatCount(repeatCount);
            }

            SimpleTrigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(jobName + "-trigger", jobGroup)
                    .withDescription(description)
                    .withSchedule(scheduleBuilder)
                    .startNow()
                    .build();

            scheduler.scheduleJob(jobDetail, trigger);
            log.info("创建简单间隔任务成功：{}.{}，间隔={}ms，重复次数={}",
                    jobGroup, jobName, intervalMs, repeatCount == -1 ? "无限" : repeatCount);
        } catch (SchedulerException e) {
            log.error("创建简单任务失败：{}.{}", jobGroup, jobName, e);
            throw ScheduleException.ofJobCreateFailed(jobGroup + "." + jobName, e);
        }
    }

    /**
     * 删除任务
     *
     * @param jobName  任务名称
     * @param jobGroup 任务组
     */
    public void deleteJob(String jobName, String jobGroup) {
        try {
            JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
            if (!scheduler.checkExists(jobKey)) {
                throw ScheduleException.ofJobNotFound(jobName, jobGroup);
            }

            scheduler.deleteJob(jobKey);
            log.info("删除任务成功：{}.{}", jobGroup, jobName);
        } catch (SchedulerException e) {
            log.error("删除任务失败：{}.{}", jobGroup, jobName, e);
            throw ScheduleException.ofJobDeleteFailed(jobGroup + "." + jobName, e);
        }
    }

    /**
     * 暂停任务
     *
     * @param jobName  任务名称
     * @param jobGroup 任务组
     */
    public void pauseJob(String jobName, String jobGroup) {
        try {
            JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
            if (!scheduler.checkExists(jobKey)) {
                throw ScheduleException.ofJobNotFound(jobName, jobGroup);
            }

            scheduler.pauseJob(jobKey);
            log.info("暂停任务：{}.{}", jobGroup, jobName);
        } catch (SchedulerException e) {
            log.error("暂停任务失败：{}.{}", jobGroup, jobName, e);
            throw ScheduleException.ofJobPauseFailed(jobGroup + "." + jobName, e);
        }
    }

    /**
     * 恢复任务
     *
     * @param jobName  任务名称
     * @param jobGroup 任务组
     */
    public void resumeJob(String jobName, String jobGroup) {
        try {
            JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
            if (!scheduler.checkExists(jobKey)) {
                throw ScheduleException.ofJobNotFound(jobName, jobGroup);
            }

            scheduler.resumeJob(jobKey);
            log.info("恢复任务：{}.{}", jobGroup, jobName);
        } catch (SchedulerException e) {
            log.error("恢复任务失败：{}.{}", jobGroup, jobName, e);
            throw ScheduleException.ofJobResumeFailed(jobGroup + "." + jobName, e);
        }
    }

    /**
     * 立即执行任务（一次性）
     *
     * @param jobName  任务名称
     * @param jobGroup 任务组
     */
    public void triggerJob(String jobName, String jobGroup) {
        try {
            JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
            if (!scheduler.checkExists(jobKey)) {
                throw ScheduleException.ofJobNotFound(jobName, jobGroup);
            }

            scheduler.triggerJob(jobKey);
            log.info("手动触发任务：{}.{}", jobGroup, jobName);
        } catch (SchedulerException e) {
            log.error("触发任务失败：{}.{}", jobGroup, jobName, e);
            throw ScheduleException.ofJobTriggerFailed(jobGroup + "." + jobName, e);
        }
    }

    /**
     * 更新任务（支持 Cron 和 Simple 两种类型）
     * cronExpression 和 intervalMs 二选一
     *
     * @param jobName        任务名称
     * @param jobGroup       任务组
     * @param cronExpression 新的 Cron 表达式（更新为 Cron 任务）
     * @param intervalMs     间隔时间（毫秒，更新为 Simple 任务）
     * @param repeatCount    重复次数（-1 表示无限重复）
     * @param description    任务描述
     * @param jobData        任务参数
     */
    public void updateJob(String jobName, String jobGroup, String cronExpression,
                          Long intervalMs, Integer repeatCount, String description, String jobData) {
        Assert.notBlank(jobName, "schedule job name required");
        Assert.notBlank(jobGroup, "schedule job group required");

        try {
            JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName + "-trigger", jobGroup);

            // 检查任务是否存在
            JobDetail oldJobDetail = scheduler.getJobDetail(jobKey);
            if (oldJobDetail == null) {
                throw ScheduleException.ofJobNotFound(jobName, jobGroup);
            }

            Trigger oldTrigger = scheduler.getTrigger(triggerKey);
            if (oldTrigger == null) {
                throw ScheduleException.ofJobNotFound(jobName, jobGroup);
            }

            // 更新 JobDetail（description 和 jobData）
            JobDataMap dataMap = new JobDataMap();
            if (jobData != null && !jobData.isEmpty()) {
                dataMap.put(ScheduleConstant.QUARTZ_JOB_PARAM_NAME, jobData);
            }

            JobDetail newJobDetail = JobBuilder.newJob(oldJobDetail.getJobClass())
                    .withIdentity(jobKey)
                    .withDescription(description != null ? description : oldJobDetail.getDescription())
                    .usingJobData(dataMap)
                    .storeDurably(true)
                    .build();

            // 更新 JobDetail（replace = true）
            scheduler.addJob(newJobDetail, true);

            Trigger newTrigger;

            // 判断更新类型：优先 Cron，其次 Simple
            if (cronExpression != null && !cronExpression.isEmpty()) {
                // 更新为 Cron 任务
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression)
                        .withMisfireHandlingInstructionDoNothing();

                newTrigger = TriggerBuilder.newTrigger()
                        .withIdentity(triggerKey)
                        .withDescription(description != null ? description : oldTrigger.getDescription())
                        .withSchedule(scheduleBuilder)
                        .build();

                log.info("更新任务为 Cron 类型：{}.{}，Cron={}", jobGroup, jobName, cronExpression);

            } else if (intervalMs != null && intervalMs > 0) {
                // 更新为 Simple 任务
                int repeat = (repeatCount != null) ? repeatCount : -1;

                SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInMilliseconds(intervalMs)
                        .withMisfireHandlingInstructionNextWithExistingCount();

                if (repeat == -1) {
                    scheduleBuilder.repeatForever();
                } else {
                    scheduleBuilder.withRepeatCount(repeat);
                }

                newTrigger = TriggerBuilder.newTrigger()
                        .withIdentity(triggerKey)
                        .withDescription(description != null ? description : oldTrigger.getDescription())
                        .withSchedule(scheduleBuilder)
                        .startNow()
                        .build();

                log.info("更新任务为 Simple 类型：{}.{}，间隔={}ms，重复次数={}",
                        jobGroup, jobName, intervalMs, repeat == -1 ? "无限" : repeat);

            } else {
                throw new IllegalArgumentException("必须指定 cronExpression 或 intervalMs");
            }

            scheduler.rescheduleJob(triggerKey, newTrigger);

        } catch (SchedulerException e) {
            log.error("更新任务失败：{}.{}", jobGroup, jobName, e);
            throw ScheduleException.ofJobUpdateFailed(jobGroup + "." + jobName, e);
        }
    }

    /**
     * 查询所有任务
     *
     * @return 任务详情列表
     */
    public List<QuartzJobResponse> listAllJobs() {
        try {
            List<QuartzJobResponse> result = new ArrayList<>();

            for (String groupName : scheduler.getJobGroupNames()) {
                for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
                    QuartzJobResponse jobInfo = getJobInfo(jobKey);
                    result.add(jobInfo);
                }
            }

            return result;
        } catch (SchedulerException e) {
            log.error("查询任务列表失败", e);
            throw new ScheduleException(org.evolboot.schedule.exception.ErrorCode.JOB_CREATE_FAILED, "Failed to list jobs: " + e.getMessage(), e);
        }
    }

    /**
     * 查询任务详情
     *
     * @param jobName  任务名称
     * @param jobGroup 任务组
     * @return 任务详情
     */
    public QuartzJobResponse getJobDetail(String jobName, String jobGroup) {
        try {
            JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
            if (!scheduler.checkExists(jobKey)) {
                throw ScheduleException.ofJobNotFound(jobName, jobGroup);
            }

            return getJobInfo(jobKey);
        } catch (SchedulerException e) {
            log.error("查询任务详情失败：{}.{}", jobGroup, jobName, e);
            throw new ScheduleException(org.evolboot.schedule.exception.ErrorCode.JOB_CREATE_FAILED, "Failed to get job detail: " + e.getMessage(), e);
        }
    }

    /**
     * 获取任务信息
     */
    private QuartzJobResponse getJobInfo(JobKey jobKey) throws SchedulerException {
        QuartzJobResponse response = new QuartzJobResponse();

        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);

        response.setJobName(jobKey.getName());
        response.setJobGroup(jobKey.getGroup());
        response.setDescription(jobDetail.getDescription());
        response.setJobClass(jobDetail.getJobClass().getName());
        response.setJobData((String) jobDetail.getJobDataMap().get(ScheduleConstant.QUARTZ_JOB_PARAM_NAME));

        if (!triggers.isEmpty()) {
            Trigger trigger = triggers.get(0);
            Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());

            response.setTriggerName(trigger.getKey().getName());
            response.setTriggerGroup(trigger.getKey().getGroup());
            response.setTriggerState(triggerState.name());
            response.setPaused(triggerState == Trigger.TriggerState.PAUSED);
            response.setPreviousFireTime(trigger.getPreviousFireTime());
            response.setNextFireTime(trigger.getNextFireTime());

            if (trigger instanceof CronTrigger) {
                response.setTriggerType("CRON");
                response.setCronExpression(((CronTrigger) trigger).getCronExpression());
            } else if (trigger instanceof SimpleTrigger) {
                response.setTriggerType("SIMPLE");
                response.setRepeatInterval(((SimpleTrigger) trigger).getRepeatInterval());
                response.setRepeatCount(((SimpleTrigger) trigger).getRepeatCount());
            }
        }

        return response;
    }
}
