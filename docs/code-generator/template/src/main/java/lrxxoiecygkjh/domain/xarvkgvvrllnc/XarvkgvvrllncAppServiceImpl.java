package projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc;

import projectPackage.core.data.Page;
import projectPackage.core.data.Sort;
import projectPackage.core.exception.DomainNotFoundException;
import projectPackage.lrxxoiecygkjh.LrxxoiecygkjhAccessAuthorities;
import projectPackage.lrxxoiecygkjh.LrxxoiecygkjhI18nMessage;
import projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.repository.XarvkgvvrllncRepository;
import projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.service.XarvkgvvrllncCreateFactory;
import projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.service.XarvkgvvrllncSupportService;
import projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.service.XarvkgvvrllncUpdateService;

import projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.entity.Xarvkgvvrllnc;
import projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.service.XarvkgvvrllncQuery;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.List;

/**
 * 模板
 *
 * @author authorxRQXP
 * @date datePlaceholder
 */
@Slf4j
@Service
public class XarvkgvvrllncAppServiceImpl extends XarvkgvvrllncSupportService implements XarvkgvvrllncAppService {


    private final XarvkgvvrllncCreateFactory factory;
    private final XarvkgvvrllncUpdateService updateService;

    protected XarvkgvvrllncAppServiceImpl(XarvkgvvrllncRepository repository, XarvkgvvrllncCreateFactory factory, XarvkgvvrllncUpdateService updateService) {
        super(repository);
        this.factory = factory;
        this.updateService = updateService;
    }

    @Override
    @Transactional
    public Xarvkgvvrllnc create(XarvkgvvrllncCreateFactory.Request request) {
        return factory.execute(request);
    }


    @Override
    @Transactional
    public void update(Keya2Akk5iV3n id, XarvkgvvrllncUpdateService.Request request) {
        updateService.execute(id, request);
    }


    @Override
    @Transactional
    public void delete(Keya2Akk5iV3n id) {
        findById(id);
        repository.deleteById(id);
    }


    @Override
    public List<Xarvkgvvrllnc> findAll() {
        return repository.findAll();
    }


    @Override
    public List<Xarvkgvvrllnc> findAll(XarvkgvvrllncQuery query) {
        return repository.findAll(query);
    }

    @Override
    public Page<Xarvkgvvrllnc> page(XarvkgvvrllncQuery query) {
        return repository.page(query);
    }


    @Override
    public Optional<Xarvkgvvrllnc> findOne(XarvkgvvrllncQuery query) {
        return repository.findOne(query);
    }
}
