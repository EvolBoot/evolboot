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
import projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.dto.XarvkgvvrllncQueryRequest;


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
public class XarvkgvvrllncAppServiceImpl  implements XarvkgvvrllncAppService {


    private final XarvkgvvrllncCreateFactory factory;
    private final XarvkgvvrllncUpdateService updateService;

    private final XarvkgvvrllncRepository repository;

    private final XarvkgvvrllncSupportService supportService;

    protected XarvkgvvrllncAppServiceImpl(XarvkgvvrllncRepository repository, XarvkgvvrllncCreateFactory factory, XarvkgvvrllncUpdateService updateService, XarvkgvvrllncSupportService supportService) {
        this.factory = factory;
        this.updateService = updateService;
        this.repository = repository;
        this.supportService = supportService;
    }

    public Xarvkgvvrllnc findById(Keya2Akk5iV3n id) {
        return supportService.findById(id);
    }

    @Override
    @Transactional
    public Xarvkgvvrllnc create(XarvkgvvrllncCreateFactory.Request request) {
        return factory.execute(request);
    }


    @Override
    @Transactional
    public void update(XarvkgvvrllncUpdateService.Request request) {
        updateService.execute(request);
    }


    @Override
    @Transactional
    public void delete(Keya2Akk5iV3n id) {
        findById(id);
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAllByIdInBatch(Iterable<Keya2Akk5iV3n> ids) {
        repository.deleteAllByIdInBatch(ids);
    }

}
