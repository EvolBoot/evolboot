package org.evolboot.schedule.exception;

/**
 * Schedule 模块错误码
 *
 * @author evol
 */
public interface ErrorCode {

    /**
     * 任务名称不能为空
     */
    Integer JOB_NAME_REQUIRED = 19001;

    /**
     * 任务组不能为空
     */
    Integer JOB_GROUP_REQUIRED = 19002;

    /**
     * 任务类不能为空
     */
    Integer JOB_CLASS_REQUIRED = 19003;

    /**
     * Cron 表达式不能为空
     */
    Integer JOB_CRON_REQUIRED = 19004;

    /**
     * 任务已存在
     */
    Integer JOB_ALREADY_EXISTS = 19005;

    /**
     * 任务不存在
     */
    Integer JOB_NOT_FOUND = 19006;

    /**
     * 任务创建失败
     */
    Integer JOB_CREATE_FAILED = 19007;

    /**
     * 任务删除失败
     */
    Integer JOB_DELETE_FAILED = 19008;

    /**
     * 任务暂停失败
     */
    Integer JOB_PAUSE_FAILED = 19009;

    /**
     * 任务恢复失败
     */
    Integer JOB_RESUME_FAILED = 19010;

    /**
     * 任务触发失败
     */
    Integer JOB_TRIGGER_FAILED = 19011;

    /**
     * 任务更新失败
     */
    Integer JOB_UPDATE_FAILED = 19012;

    /**
     * Cron 表达式无效
     */
    Integer INVALID_CRON_EXPRESSION = 19013;

    /**
     * 任务类无效
     */
    Integer INVALID_JOB_CLASS = 19014;
}
