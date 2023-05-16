package org.evolboot.system.domain.notice.repository;

import org.evolboot.core.data.BaseRepository;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.Query;
import org.evolboot.system.domain.notice.Notice;

import java.util.List;
import java.util.Optional;

/**
 * 公告
 *
 * @author evol
 */
public interface NoticeRepository extends BaseRepository<Notice, Long> {

    Notice save(Notice notice);

    Optional<Notice> findById(Long id);


    void deleteById(Long id);

    List<Notice> findAll();


    Optional<Notice> findByLatest();

    <Q extends Query> List<Notice> findAll(Q query);

    <Q extends Query> Optional<Notice> findOne(Q query);

    <Q extends Query> long count(Q query);

    <Q extends Query> Page<Notice> page(Q query);


}
