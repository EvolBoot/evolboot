package org.evolboot.schedule.domain.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Quartz Job 基类
 * 提供统一的日志记录和异常处理
 *
 * @author evol
 */
@Slf4j
public abstract class BaseQuartzJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        String jobName = context.getJobDetail().getKey().getName();
        String jobGroup = context.getJobDetail().getKey().getGroup();

        long startTime = System.currentTimeMillis();
        log.info("定时任务开始执行：{}.{}", jobGroup, jobName);

        try {
            executeInternal(context);
            long duration = System.currentTimeMillis() - startTime;
            log.info("定时任务执行成功：{}.{}，耗时：{}ms", jobGroup, jobName, duration);
        } catch (Exception e) {
            long duration = System.currentTimeMillis() - startTime;
            log.error("定时任务执行失败：{}.{}，耗时：{}ms", jobGroup, jobName, duration, e);

            // 将异常包装为 JobExecutionException，以便 Quartz 处理
            JobExecutionException jobException = new JobExecutionException(e);
            // 设置是否重新执行（可由子类覆盖）
            jobException.setRefireImmediately(shouldRefireOnError());
            throw jobException;
        }
    }

    /**
     * 子类实现具体的任务逻辑
     *
     * @param context 任务执行上下文
     * @throws Exception 任务执行异常
     */
    protected abstract void executeInternal(JobExecutionContext context) throws Exception;

    /**
     * 出错时是否立即重新执行
     * 子类可覆盖此方法
     *
     * @return true=立即重新执行，false=不重新执行
     */
    protected boolean shouldRefireOnError() {
        return false;
    }
}
