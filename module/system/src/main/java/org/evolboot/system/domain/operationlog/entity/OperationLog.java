package org.evolboot.system.domain.operationlog.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.data.jpa.JpaAbstractEntity;
import org.evolboot.core.entity.AggregateRoot;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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
     * 请求结果
     */
    @Schema(description = "请求结果")
    private String result;

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


    /**
     * 状态
     */
    private Boolean state;

    @Builder
    public OperationLog(Long id, Long userId, Long beginTime, Long endTime, String operation, String httpMethod, String classMethod, String requestUrl, String params, String result, Long time, String ip, Boolean state) {
        this.id = id;
        this.userId = userId;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.operation = operation;
        this.httpMethod = httpMethod;
        this.classMethod = classMethod;
        this.requestUrl = requestUrl;
        this.params = params;
        this.result = result;
        this.time = time;
        this.ip = ip;
        this.state = state;
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
