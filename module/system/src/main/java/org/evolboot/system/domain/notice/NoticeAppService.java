package org.evolboot.system.domain.notice;

import org.evolboot.system.domain.notice.service.NoticeCreateFactory;
import org.evolboot.system.domain.notice.service.NoticeUpdateService;
import org.evolboot.core.data.Page;

import java.util.List;

/**
 * 公告
 *
 * @author evol
 */
public interface NoticeAppService {

    Notice create(NoticeCreateFactory.Request request);

    void update(Long id, NoticeUpdateService.Request request);

    void delete(Long id);

    List<Notice> findAll();

    List<Notice> findAll(NoticeQuery query);

    Page<Notice> page(NoticeQuery query);

    Notice findById(Long id);

    Notice findByLatest();


}
