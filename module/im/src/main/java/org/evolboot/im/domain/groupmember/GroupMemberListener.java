package org.evolboot.im.domain.groupmember;

import org.evolboot.im.domain.groupmember.repository.GroupMemberRepository;
import org.evolboot.im.domain.groupmember.service.GroupMemberSupportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 群成员
 *
 * @author evol
 * @date 2023-05-03 16:20:09
 */
@Service
@Slf4j
public class GroupMemberListener extends GroupMemberSupportService {

    protected GroupMemberListener(GroupMemberRepository repository) {
        super(repository);
    }



}
