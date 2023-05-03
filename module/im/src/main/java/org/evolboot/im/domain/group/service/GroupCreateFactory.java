package org.evolboot.im.domain.group.service;


import org.springframework.stereotype.Service;
import org.evolboot.im.domain.group.repository.GroupRepository;
import org.evolboot.im.domain.group.Group;
import org.evolboot.im.domain.group.
        GroupRequestBase;
import lombok.extern.slf4j.Slf4j;

/**
 * 群组
 *
 * @author evol
 * @date 2023-05-03 15:52:47
 */
@Slf4j
@Service
public class GroupCreateFactory extends GroupSupportService {
    protected GroupCreateFactory(GroupRepository repository) {
        super(repository);
    }

    public Group execute(Request request) {
        Group group = new Group("test");
        repository.save(group);
        return group;
    }

    public static class Request extends GroupRequestBase {

    }

}
