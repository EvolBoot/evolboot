package org.evolboot.storage.domain.blob.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.evolboot.core.data.Direction;
import org.evolboot.core.data.Query;
import org.evolboot.storage.domain.blob.entity.BlobType;
import org.evolboot.storage.domain.blob.entity.StorageType;
import org.evolboot.storage.domain.blob.intercept.FileLimitType;

import java.util.Date;

/**
 * @author evol
 */
@Setter
@Getter
public class BlobQueryRequest extends Query {

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
    private Long ownerUserId;

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


    @Builder
    public BlobQueryRequest(Integer page, Integer limit, String sortField, Direction direction,
                            Long id, String name, String originalName, String extension,
                            BlobType type, StorageType storageType, Long ownerUserId,
                            Date createAtStart, Date createAtEnd, Long minSize, Long maxSize, FileLimitType fileType) {
        super(page, limit, sortField, direction);
        this.id = id;
        this.name = name;
        this.originalName = originalName;
        this.extension = extension;
        this.type = type;
        this.storageType = storageType;
        this.ownerUserId = ownerUserId;
        this.createAtStart = createAtStart;
        this.createAtEnd = createAtEnd;
        this.minSize = minSize;
        this.maxSize = maxSize;
        this.fileType = fileType;
    }
}
