package org.evolboot.schedule.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.schedule.ScheduleConstant;
import org.evolboot.schedule.domain.quartz.BaseQuartzJob;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;

/**
 * 演示 Quartz 任务
 * 用于测试 Quartz 集成是否正常工作
 *
 * @author evol
 */
@Slf4j
@DisallowConcurrentExecution
public class QuartzJobDemoJob extends BaseQuartzJob {

    @Override
    public String getJobName() {
        return "DemoJob";
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws Exception {
        String jobName = context.getJobDetail().getKey().getName();
        String jobGroup = context.getJobDetail().getKey().getGroup();

        log.info("DemoJob 正在执行：{}.{}", jobGroup, jobName);

        // 从 JobDataMap 获取参数
        String message = getJobParam(context);
        if (message != null) {
            log.info("DemoJob 接收到参数：{}", message);
        }

        // 模拟业务处理
        Thread.sleep(1000 * 60);

        log.info("DemoJob 执行完成：{}.{}", jobGroup, jobName);
    }

    @Override
    protected boolean shouldRefireOnError() {
        // 如果出错，不立即重新执行
        return false;
    }
}
