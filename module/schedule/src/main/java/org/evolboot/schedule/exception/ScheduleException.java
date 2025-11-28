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

    public static ScheduleException ofJobCreateFailed(String jobName, Throwable cause) {
        return new ScheduleException(ErrorCode.JOB_CREATE_FAILED, "Failed to create job [" + jobName + "]: " + cause.getMessage(), cause);
    }

    public static ScheduleException ofJobDeleteFailed(String jobName, Throwable cause) {
        return new ScheduleException(ErrorCode.JOB_DELETE_FAILED, "Failed to delete job [" + jobName + "]: " + cause.getMessage(), cause);
    }

    public static ScheduleException ofJobPauseFailed(String jobName, Throwable cause) {
        return new ScheduleException(ErrorCode.JOB_PAUSE_FAILED, "Failed to pause job [" + jobName + "]: " + cause.getMessage(), cause);
    }

    public static ScheduleException ofJobResumeFailed(String jobName, Throwable cause) {
        return new ScheduleException(ErrorCode.JOB_RESUME_FAILED, "Failed to resume job [" + jobName + "]: " + cause.getMessage(), cause);
    }

    public static ScheduleException ofJobTriggerFailed(String jobName, Throwable cause) {
        return new ScheduleException(ErrorCode.JOB_TRIGGER_FAILED, "Failed to trigger job [" + jobName + "]: " + cause.getMessage(), cause);
    }

    public static ScheduleException ofJobUpdateFailed(String jobName, Throwable cause) {
        return new ScheduleException(ErrorCode.JOB_UPDATE_FAILED, "Failed to update job [" + jobName + "]: " + cause.getMessage(), cause);
    }

    public static ScheduleException ofInvalidJobClass(String className) {
        return new ScheduleException(ErrorCode.INVALID_JOB_CLASS, "Invalid job class: " + className);
    }

    public static ScheduleException ofJobAlreadyExists(String jobName, String jobGroup) {
        return new ScheduleException(ErrorCode.JOB_ALREADY_EXISTS, "Job already exists: " + jobGroup + "." + jobName);
    }

    public static ScheduleException ofJobNotFound(String jobName, String jobGroup) {
        return new ScheduleException(ErrorCode.JOB_NOT_FOUND, "Job not found: " + jobGroup + "." + jobName);
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

    public ScheduleException(int errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }
}
