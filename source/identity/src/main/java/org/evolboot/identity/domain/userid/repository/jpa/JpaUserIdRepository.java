package org.evolboot.identity.domain.userid.repository.jpa;

import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.core.data.jpa.querydsl.ExtendedQuerydslPredicateExecutor;
import org.evolboot.identity.domain.userid.QUserId;
import org.evolboot.identity.domain.userid.UserId;
import org.evolboot.identity.domain.userid.UserIdQuery;
import org.evolboot.identity.domain.userid.repository.UserIdRepository;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserId
 *
 * @author evol
 * 
 */
@Repository
public interface JpaUserIdRepository extends UserIdRepository, ExtendedQuerydslPredicateExecutor<UserId>, JpaRepository<UserId, Long> {

    default JPQLQuery<UserId> fillQueryParameter(UserIdQuery query) {
        QUserId q = QUserId.userId;
        JPQLQuery<UserId> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(q).from(q);
        return jpqlQuery;
    }

    @Override
    default List<UserId> findAll() {
        QUserId q = QUserId.userId;
        JPQLQuery<UserId> jpqlQuery = getJPQLQuery();
        jpqlQuery.select(q).from(q);
        return this.findAll(jpqlQuery);
    }

    @Override
    default List<UserId> findAll(UserIdQuery query) {
        JPQLQuery<UserId> jpqlQuery = fillQueryParameter(query);
        return findAll(jpqlQuery);
    }


    @Override
    default Page<UserId> page(UserIdQuery query) {
        JPQLQuery<UserId> jpqlQuery = fillQueryParameter(query);
        return PageImpl.of(this.findAll(jpqlQuery, query.toJpaPageRequest()));
    }

    @Override
    @Query(nativeQuery = true, value = "select id_ from evol_identity_user_id where  status_ = 0  ORDER BY (37*(UNIX_TIMESTAMP() ^ id_)) & 0xffff   limit :num")
    List<Long> rand(@Param("num") int num);


}
