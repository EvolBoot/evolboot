package org.evolboot.storage.domain.blob.repository.jpa;

import com.querydsl.jpa.JPQLQuery;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.core.data.jpa.querydsl.ExtendedQuerydslPredicateExecutor;
import org.evolboot.storage.domain.blob.entity.Blob;
import org.evolboot.storage.domain.blob.service.BlobQuery;
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
    default Page<Blob> page(BlobQuery query) {

        QBlob q = QBlob.blob;
        JPQLQuery<Blob> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(q).from(q).orderBy(q.id.desc());
        return PageImpl.of(this.findAll(jpqlQuery, query.toJpaPageRequest()));

    }
}
