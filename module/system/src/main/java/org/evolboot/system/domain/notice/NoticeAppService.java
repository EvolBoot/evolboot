package org.evolboot.system.domain.notice;

import org.evolboot.core.data.Page;
import org.evolboot.system.domain.notice.entity.Notice;
import org.evolboot.system.domain.notice.service.NoticeCreateFactory;
import org.evolboot.system.domain.notice.service.NoticeQuery;
import org.evolboot.system.domain.notice.service.NoticeUpdateService;

import java.util.List;

/**
 * 公告
 *
 * @author evol
 */
public interface NoticeAppService {

    Notice create(NoticeCreateFactory.Request request);

    void update(NoticeUpdateService.Request request);

    void delete(Long id);

    List<Notice> findAll();

    List<Notice> findAll(NoticeQuery query);

    Page<Notice> page(NoticeQuery query);

    Notice findById(Long id);

    Notice findByLatest();


}
