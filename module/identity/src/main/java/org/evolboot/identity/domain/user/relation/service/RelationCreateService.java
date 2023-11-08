package org.evolboot.identity.domain.user.relation.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.identity.domain.user.relation.repository.RelationRepository;
import org.springframework.stereotype.Service;

/**
 * @author evol
 */
@Service
@Slf4j
public class RelationCreateService {
    private final RelationRepository repository;
    private final RelationSupportService supportService;


    public RelationCreateService(RelationRepository repository, RelationSupportService supportService) {
        this.repository = repository;
        this.supportService = supportService;
    }

    public void execute(Long ancestor, Long descendant) {
        if (ExtendObjects.nonNull(ancestor) && ancestor != 0) {
            repository.insertPath(descendant, ancestor);
        }
        supportService.saveSelf(descendant);
    }


}
