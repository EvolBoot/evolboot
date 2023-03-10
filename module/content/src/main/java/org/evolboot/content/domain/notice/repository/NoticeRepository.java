package org.evolboot.content.domain.notice.repository;

import org.evolboot.content.domain.notice.Notice;
import org.evolboot.content.domain.notice.NoticeQuery;
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
}
