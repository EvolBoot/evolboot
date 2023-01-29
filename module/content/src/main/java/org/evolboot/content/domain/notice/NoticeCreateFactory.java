package org.evolboot.content.domain.notice;

import org.evolboot.content.domain.notice.repository.NoticeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 公告
 *
 * @author evol
 */
@Slf4j
@Service
public class NoticeCreateFactory extends NoticeSupportService {
    protected NoticeCreateFactory(NoticeRepository repository) {
        super(repository);
    }

    public Notice execute(Request request) {
        Notice notice = new Notice(
                request.getSort(),
                request.getEnable(),
                request.getReleasedTime(),
                request.getLocales()
        );
        repository.save(notice);
        return notice;
    }

    public static class Request extends NoticeRequestBase {

    }

}
