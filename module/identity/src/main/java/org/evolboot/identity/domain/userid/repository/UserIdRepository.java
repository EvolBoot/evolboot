package org.evolboot.identity.domain.userid.repository;

import org.evolboot.core.data.BaseRepository;
import org.evolboot.core.data.Page;
import org.evolboot.identity.domain.userid.dto.UserIdQueryRequest;
import org.evolboot.identity.domain.userid.entity.UserId;
import org.evolboot.identity.domain.userid.dto.UserIdQueryRequest;

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

    Page<UserId> page(UserIdQueryRequest query);

    void deleteById(Long id);

    List<UserId> findAll();

    List<UserId> findAll(UserIdQueryRequest query);

    <S extends UserId> List<S> saveAll(Iterable<S> userIds);

    List<Long> rand(int num);

    Long countByState(Boolean state);

    /**
     * 根据条件查询单个
     *
     * @param query
     * @return
     */
    Optional<UserId> findOne(UserIdQueryRequest query);


}
