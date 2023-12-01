package org.evolboot.identity.domain.user.relation;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.data.Page;
import org.evolboot.identity.domain.user.relation.dto.RelationQueryRequest;
import org.evolboot.identity.domain.user.relation.repository.RelationRepository;
import org.evolboot.identity.domain.user.relation.service.RelationCreateService;
import org.evolboot.identity.domain.user.relation.service.RelationMoveService;
import org.evolboot.identity.domain.user.relation.service.RelationMoveTreeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author evol
 */
@Service
@Slf4j
public class RelationAppServiceImpl implements RelationAppService {

    private final RelationCreateService relationCreateService;
    private final RelationMoveTreeService relationMoveTreeService;
    private final RelationMoveService relationMoveService;
    private final RelationRepository repository;

    public RelationAppServiceImpl(RelationCreateService relationCreateService, RelationMoveTreeService relationMoveTreeService, RelationMoveService relationMoveService, RelationRepository repository) {
        this.relationCreateService = relationCreateService;
        this.relationMoveTreeService = relationMoveTreeService;
        this.relationMoveService = relationMoveService;
        this.repository = repository;
    }


    @Override
    public Page<Relation> page(RelationQueryRequest query) {
        return repository.page(query);
    }

    @Override
    @Transactional
    public void create(Long ancestor, Long descendant) {
        relationCreateService.execute(ancestor, descendant);
    }

    @Override
    @Transactional
    public void move(Long id, Long target) {
        relationMoveService.execute(id, target);
    }

    @Override
    @Transactional
    public void moveTree(Long id, Long target) {
        relationMoveTreeService.execute(id, target);
    }

    @Override
    @Transactional
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public Long countByAncestorAndDistance(Long ancestor, Integer distance) {
        return repository.countByAncestorAndDistance(ancestor, distance);
    }

    @Override
    public List<Long> findDescendantByAncestorAndDistance(Long ancestor, Integer distance) {

        return null;
    }


    @Override
    public List<Long> findAllAncestorIdAndOrderByDistance(Long descendant, Integer goeDistance, Integer limit) {
        return repository.findAllAncestorIdAndOrderByDistance(descendant, goeDistance, limit);
    }


}
