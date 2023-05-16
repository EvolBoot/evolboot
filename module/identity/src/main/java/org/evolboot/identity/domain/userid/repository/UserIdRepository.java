package org.evolboot.identity.domain.userid.repository;

import org.evolboot.core.data.BaseRepository;
import org.evolboot.core.data.Page;
import org.evolboot.identity.domain.userid.UserId;
import org.evolboot.identity.domain.userid.UserIdQuery;

import java.util.List;
import java.util.Optional;

/**
 * UserId
 *
 * @author evol
 */
public interface UserIdRepository extends BaseRepository<UserId, Long> {

    UserId save(UserId userId);

    Optional<UserId> findById(Long id);

    Page<UserId> page(UserIdQuery query);

    void deleteById(Long id);

    List<UserId> findAll();

    List<UserId> findAll(UserIdQuery query);

    <S extends UserId> List<S> saveAll(Iterable<S> userIds);

    List<Long> rand(int num);

    Long countByStatus(Boolean status);

    /**
     * 根据条件查询单个
     *
     * @param query
     * @return
     */
    Optional<UserId> findOne(UserIdQuery query);


}
