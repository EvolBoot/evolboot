package org.evolboot.im.domain.groupmember.service;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.im.domain.groupmember.entity.GroupMember;
import org.evolboot.im.domain.groupmember.repository.GroupMemberRepository;
import org.springframework.stereotype.Service;

/**
 * 群成员
 *
 * @author evol
 * @date 2023-05-03 16:20:09
 */
@Slf4j
@Service
public class GroupMemberUpdateService extends GroupMemberSupportService {
    protected GroupMemberUpdateService(GroupMemberRepository repository) {
        super(repository);
    }

    public void execute(Request request) {
        GroupMember groupMember = findById(request.getId());
        repository.save(groupMember);
    }

    @Getter
    @Setter
    public static class Request extends GroupMemberRequestBase {
        private Long id;
    }

}
