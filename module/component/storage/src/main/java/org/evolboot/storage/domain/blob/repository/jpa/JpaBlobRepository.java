package org.evolboot.storage.domain.blob.repository.jpa;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.core.data.jpa.querydsl.ExtendedQuerydslPredicateExecutor;
import org.evolboot.storage.domain.blob.entity.Blob;
import org.evolboot.storage.domain.blob.dto.BlobQueryRequest;
import org.evolboot.storage.domain.blob.entity.QBlob;
import org.evolboot.storage.domain.blob.repository.BlobRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author evol
 */
@Repository
public interface JpaBlobRepository extends BlobRepository, ExtendedQuerydslPredicateExecutor<Blob, Long>, JpaRepository<Blob, Long> {

    @Override
    default Page<Blob> page(BlobQueryRequest query) {

        QBlob q = QBlob.blob;
        JPQLQuery<Blob> jpqlQuery = getJPQLQuery();

        // 构建查询条件
        BooleanBuilder builder = new BooleanBuilder();

        // ID 精确查询
        if (query.getId() != null) {
            builder.and(q.id.eq(query.getId()));
        }

        // 文件名模糊查询
        if (query.getName() != null && !query.getName().isEmpty()) {
            builder.and(q.name.containsIgnoreCase(query.getName()));
        }

        // 原始文件名模糊查询
        if (query.getOriginalName() != null && !query.getOriginalName().isEmpty()) {
            builder.and(q.originalName.containsIgnoreCase(query.getOriginalName()));
        }

        // 文件扩展名精确查询
        if (query.getExtension() != null && !query.getExtension().isEmpty()) {
            builder.and(q.extension.eq(query.getExtension()));
        }

        // 文件类型查询
        if (query.getType() != null) {
            builder.and(q.type.eq(query.getType()));
        }

        // 存储类型查询
        if (query.getStorageType() != null) {
            builder.and(q.storageType.eq(query.getStorageType()));
        }

        // 所有者用户ID查询
        if (query.getOwnerUserId() != null) {
            builder.and(q.ownerUserId.eq(query.getOwnerUserId()));
        }

        // 创建时间范围查询
        if (query.getCreateAtStart() != null) {
            builder.and(q.createAt.goe(query.getCreateAtStart()));
        }
        if (query.getCreateAtEnd() != null) {
            builder.and(q.createAt.loe(query.getCreateAtEnd()));
        }

        // 文件大小范围查询
        if (query.getMinSize() != null) {
            builder.and(q.size.goe(query.getMinSize()));
        }
        if (query.getMaxSize() != null) {
            builder.and(q.size.loe(query.getMaxSize()));
        }

        if (query.getFileType() != null) {
            builder.and(q.fileType.eq(query.getFileType()));
        }

        jpqlQuery.select(q).from(q).where(builder).orderBy(q.id.desc());
        return PageImpl.of(this.findAll(jpqlQuery, query.toJpaPageRequest()));

    }
}
