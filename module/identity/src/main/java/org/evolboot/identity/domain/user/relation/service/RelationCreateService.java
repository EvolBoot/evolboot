package org.evolboot.identity.domain.user.relation.service;

import org.evolboot.core.util.ExtendObjects;
import org.evolboot.identity.domain.user.relation.repository.RelationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author evol
 * 
 */
@Service
@Slf4j
public class RelationCreateService extends RelationSupportService {

    private final RelationRepository repository;

    public RelationCreateService(RelationRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public void execute(Long ancestor, Long descendant) {
        if (ExtendObjects.nonNull(ancestor) && ancestor != 0) {
            repository.insertPath(descendant, ancestor);
        }
        saveSelf(descendant);
    }


}
