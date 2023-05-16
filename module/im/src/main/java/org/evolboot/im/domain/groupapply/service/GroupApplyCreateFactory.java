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
public class GroupApplyCreateFactory extends GroupApplySupportService {
    protected GroupApplyCreateFactory(GroupApplyRepository repository) {
        super(repository);
    }

    public GroupApply execute(Request request) {
        GroupApply groupApply = new GroupApply("test");
        repository.save(groupApply);
        return groupApply;
    }

    public static class Request extends GroupApplyRequestBase {

    }

}
