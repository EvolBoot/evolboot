package org.evolboot.im.domain.groupmember.listener;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.im.domain.groupmember.repository.GroupMemberRepository;
import org.evolboot.im.domain.groupmember.service.GroupMemberSupportService;
import org.springframework.stereotype.Service;

/**
 * 群成员
 *
 * @author evol
 * @date 2023-05-03 16:20:09
 */
@Service
@Slf4j
public class GroupMemberListener {

    private final GroupMemberRepository repository;

    private final GroupMemberSupportService supportService;

    protected GroupMemberListener(GroupMemberRepository repository, GroupMemberSupportService supportService) {
        this.repository = repository;
        this.supportService = supportService;
    }


}
