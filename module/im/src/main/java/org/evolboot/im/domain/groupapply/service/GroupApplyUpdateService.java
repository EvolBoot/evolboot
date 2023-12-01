package org.evolboot.im.domain.groupapply.service;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.im.domain.groupapply.dto.GroupApplyRequestBase;
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
public class GroupApplyUpdateService {

    private final GroupApplyRepository repository;
    private final GroupApplySupportService supportService;

    protected GroupApplyUpdateService(GroupApplyRepository repository, GroupApplySupportService supportService) {
        this.repository = repository;
        this.supportService = supportService;
    }

    public void execute(Request request) {
        GroupApply groupApply = supportService.findById(request.getId());
        repository.save(groupApply);
    }

    @Getter
    @Setter
    public static class Request extends GroupApplyRequestBase {
        private Long id;
    }

}
