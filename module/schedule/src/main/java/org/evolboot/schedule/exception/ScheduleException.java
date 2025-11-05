package org.evolboot.schedule.exception;

import org.evolboot.core.exception.ExtendErrorCodeException;

/**
 * Schedule 模块异常
 *
 * @author evol
 */
public class ScheduleException extends ExtendErrorCodeException {

    public static final ScheduleException JOB_ALREADY_EXISTS = new ScheduleException(ErrorCode.JOB_ALREADY_EXISTS, "Job already exists");
    public static final ScheduleException JOB_NOT_FOUND = new ScheduleException(ErrorCode.JOB_NOT_FOUND, "Job not found");
    public static final ScheduleException INVALID_CRON_EXPRESSION = new ScheduleException(ErrorCode.INVALID_CRON_EXPRESSION, "Invalid cron expression");

    public static ScheduleException ofJobCreateFailed(String jobName, String message) {
        return new ScheduleException(ErrorCode.JOB_CREATE_FAILED, "Failed to create job [" + jobName + "]: " + message);
    }

    public static ScheduleException ofJobDeleteFailed(String jobName, String message) {
        return new ScheduleException(ErrorCode.JOB_DELETE_FAILED, "Failed to delete job [" + jobName + "]: " + message);
    }

    public static ScheduleException ofJobPauseFailed(String jobName, String message) {
        return new ScheduleException(ErrorCode.JOB_PAUSE_FAILED, "Failed to pause job [" + jobName + "]: " + message);
    }

    public static ScheduleException ofJobResumeFailed(String jobName, String message) {
        return new ScheduleException(ErrorCode.JOB_RESUME_FAILED, "Failed to resume job [" + jobName + "]: " + message);
    }

    public static ScheduleException ofJobTriggerFailed(String jobName, String message) {
        return new ScheduleException(ErrorCode.JOB_TRIGGER_FAILED, "Failed to trigger job [" + jobName + "]: " + message);
    }

    public static ScheduleException ofJobUpdateFailed(String jobName, String message) {
        return new ScheduleException(ErrorCode.JOB_UPDATE_FAILED, "Failed to update job [" + jobName + "]: " + message);
    }

    public static ScheduleException ofInvalidJobClass(String className) {
        return new ScheduleException(ErrorCode.INVALID_JOB_CLASS, "Invalid job class: " + className);
    }

    private final int errorCode;

    @Override
    public int getErrorCode() {
        return errorCode;
    }

    public ScheduleException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
