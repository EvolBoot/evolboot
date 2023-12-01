package org.evolboot.im.domain.group.service;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.im.domain.group.dto.GroupRequestBase;
import org.evolboot.im.domain.group.entity.Group;
import org.evolboot.im.domain.group.repository.GroupRepository;
import org.springframework.stereotype.Service;

/**
 * 群组
 *
 * @author evol
 * @date 2023-05-03 15:52:47
 */
@Slf4j
@Service
public class GroupUpdateService {

    private final GroupRepository repository;
    private final GroupSupportService supportService;

    protected GroupUpdateService(GroupRepository repository, GroupSupportService supportService) {
        this.repository = repository;
        this.supportService = supportService;
    }

    public void execute(Request request) {
        Group group = supportService.findById(request.getId());
        repository.save(group);
    }

    @Getter
    @Setter
    public static class Request extends GroupRequestBase {
        private Long id;
    }

}
