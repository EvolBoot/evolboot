package org.evolboot.im.domain.groupapply.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.im.domain.groupapply.GroupApply;
import org.evolboot.im.domain.groupapply.GroupApplyRequestBase;
import org.evolboot.im.domain.groupapply.repository.GroupApplyRepository;
import org.springframework.stereotype.Service;

/**
 * 群申请
 *
 * @author evol
 * @date 2023-05-03 17:27:04
 */
@Slf4j
@Service
public class GroupApplyUpdateService extends GroupApplySupportService {
    protected GroupApplyUpdateService(GroupApplyRepository repository) {
        super(repository);
    }

    public void execute(Long id, Request request) {
        GroupApply groupApply = findById(id);
        repository.save(groupApply);
    }

    public static class Request extends GroupApplyRequestBase {
    }

}
