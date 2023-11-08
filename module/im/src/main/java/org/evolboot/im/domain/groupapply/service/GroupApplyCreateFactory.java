package org.evolboot.im.domain.groupapply.service;


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
public class GroupApplyCreateFactory {

    private final GroupApplyRepository repository;
    private final GroupApplySupportService supportService;

    protected GroupApplyCreateFactory(GroupApplyRepository repository, GroupApplySupportService supportService) {
        this.repository = repository;
        this.supportService = supportService;
    }


    public GroupApply execute(Request request) {
        GroupApply groupApply = new GroupApply("test");
        repository.save(groupApply);
        return groupApply;
    }

    public static class Request extends GroupApplyRequestBase {

    }

}
