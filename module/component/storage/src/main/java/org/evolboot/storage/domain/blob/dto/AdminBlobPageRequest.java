package org.evolboot.storage.domain.blob.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.evolboot.core.data.Direction;
import org.evolboot.storage.domain.blob.entity.BlobType;
import org.evolboot.storage.domain.blob.entity.StorageType;
import org.evolboot.storage.domain.blob.intercept.FileLimitType;

import java.util.Date;

/**
 * Blob 分页查询请求参数
 * @author evol
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminBlobPageRequest {

    /**
     * 页码
     */
    private Integer page;

    /**
     * 每页数量
     */
    private Integer limit;

    /**
     * 排序字段
     */
    private String sortField;

    /**
     * 排序方向
     */
    private Direction direction;

    /**
     * 文件ID
     */
    private Long id;

    /**
     * 文件名（模糊查询）
     */
    private String name;

    /**
     * 原始文件名（模糊查询）
     */
    private String originalName;

    /**
     * 文件扩展名
     */
    private String extension;

    /**
     * 文件类型
     */
    private BlobType type;

    /**
     * 存储类型
     */
    private StorageType storageType;

    /**
     * 所有者用户ID
     */
    private Long ownerId;

    /**
     * 创建时间起始
     */
    private Date createAtStart;

    /**
     * 创建时间结束
     */
    private Date createAtEnd;

    /**
     * 最小文件大小
     */
    private Long minSize;

    /**
     * 最大文件大小
     */
    private Long maxSize;

    /**
     * 文件类型
     */
    private FileLimitType fileType;

    /**
     * 转换为 BlobQueryRequest
     */
    public BlobQueryRequest toBlobQueryRequest() {
        return BlobQueryRequest.builder()
                .page(page)
                .limit(limit)
                .sortField(sortField)
                .direction(direction)
                .id(id)
                .name(name)
                .originalName(originalName)
                .extension(extension)
                .type(type)
                .storageType(storageType)
                .ownerId(ownerId)
                .createAtStart(createAtStart)
                .createAtEnd(createAtEnd)
                .minSize(minSize)
                .maxSize(maxSize)
                .fileType(fileType)
                .build();
    }
}
