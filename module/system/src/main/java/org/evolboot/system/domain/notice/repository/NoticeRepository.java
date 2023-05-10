package org.evolboot.system.domain.notice.repository;

import org.evolboot.system.domain.notice.Notice;
import org.evolboot.system.domain.notice.NoticeQuery;
import org.evolboot.core.data.Page;

import java.util.List;
import java.util.Optional;

/**
 * 公告
 *
 * @author evol
 * 
 */
public interface NoticeRepository {

    Notice save(Notice notice);

    Optional<Notice> findById(Long id);

    Page<Notice> page(NoticeQuery query);

    void deleteById(Long id);

    List<Notice> findAll();

    List<Notice> findAll(NoticeQuery query);

    Optional<Notice> findByLatest();

    /**
     * 根据条件查询单个
     * @param query
     * @return
     */
    Optional<Notice> findOne(NoticeQuery query);

}
