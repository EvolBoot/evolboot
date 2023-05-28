package org.evolboot.im.domain.groupapply.service;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.im.domain.groupapply.entity.GroupApply;
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

    public void execute(Request request) {
        GroupApply groupApply = findById(request.getId());
        repository.save(groupApply);
    }

    @Getter
    @Setter
    public static class Request extends GroupApplyRequestBase {
        private Long id;
    }

}
