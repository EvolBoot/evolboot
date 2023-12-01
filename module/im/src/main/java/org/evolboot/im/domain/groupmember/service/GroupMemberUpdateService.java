package org.evolboot.im.domain.groupmember.service;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.im.domain.groupmember.dto.GroupMemberRequestBase;
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
public class GroupMemberUpdateService {

    private final GroupMemberRepository repository;
    private final GroupMemberSupportService supportService;

    protected GroupMemberUpdateService(GroupMemberRepository repository, GroupMemberSupportService supportService) {
        this.repository = repository;
        this.supportService = supportService;
    }

    public void execute(Request request) {
        GroupMember groupMember = supportService.findById(request.getId());
        repository.save(groupMember);
    }

    @Getter
    @Setter
    public static class Request extends GroupMemberRequestBase {
        private Long id;
    }

}
