package org.evolboot.system.domain.operationlog;

import org.evolboot.core.data.jpa.JpaAbstractEntity;
import org.evolboot.core.domain.AggregateRoot;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 操作日志
 *
 * @author evol
 */
@Entity
@Table(name = "evoltb_system_operation_log")
@Getter
@Slf4j
@NoArgsConstructor
@ToString
public class OperationLog extends JpaAbstractEntity<Long> implements AggregateRoot<OperationLog> {

    @Id
    private Long id;

    /**
     * 操作用户
     */
    private Long userId;

    /**
     * 请求开始时间
     */
    protected Long beginTime;

    /**
     * 请求结束时间
     */
    protected Long endTime;

    /**
     * 用户操作
     */
    @Schema(description = "用户操作")
    private String operation;

    /**
     * Http方法
     */
    @Schema(description = "Http方法")
    private String httpMethod;

    /**
     * 类方法
     */
    @Schema(description = "类方法")
    private String classMethod;

    /**
     * 请求方法
     */
    @Schema(description = "请求URL")
    private String requestUrl;

    /**
     * 请求参数
     */
    @Schema(description = "请求参数")
    private String params;

    /**
     * 执行时长（毫秒）
     */
    @Schema(description = "执行时长（毫秒）")
    private Long time;

    /**
     * IP地址
     */
    @Schema(description = "IP地址")
    private String ip;

    @Builder
    public OperationLog(Long id, Long userId, Long beginTime, Long endTime, String operation, String httpMethod, String classMethod, String requestUrl, String params, Long time, String ip) {
        this.id = id;
        this.userId = userId;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.operation = operation;
        this.httpMethod = httpMethod;
        this.classMethod = classMethod;
        this.requestUrl = requestUrl;
        this.params = params;
        this.time = time;
        this.ip = ip;
    }

    @Override
    public Long id() {
        return id;
    }

    @Override
    public OperationLog root() {
        return this;
    }
}
