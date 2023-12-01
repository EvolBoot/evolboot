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
 * 查询服务 模板
 *
 * @author authorxRQXP
 * @date datePlaceholder
 */
@Slf4j
@Service
public class XarvkgvvrllncQueryServiceImpl  implements XarvkgvvrllncQueryService {

    private final XarvkgvvrllncRepository repository;
    private final XarvkgvvrllncSupportService supportService;

    protected XarvkgvvrllncQueryServiceImpl(XarvkgvvrllncRepository repository, XarvkgvvrllncSupportService supportService) {
        this.repository = repository;
        this.supportService = supportService;
    }

    @Override
    public Xarvkgvvrllnc findById(Keya2Akk5iV3n id) {
        return supportService.findById(id);
    }

    @Override
    public List<Xarvkgvvrllnc> findAll() {
        return repository.findAll();
    }

    @Override
    public boolean existsById(Keya2Akk5iV3n id) {
        return repository.existsById(id);
    }
    @Override
    public List<Xarvkgvvrllnc> findAll(XarvkgvvrllncQueryRequest query) {
        return repository.findAll(query);
    }

    @Override
    public Page<Xarvkgvvrllnc> page(XarvkgvvrllncQueryRequest query) {
        return repository.page(query);
    }


    @Override
    public Optional<Xarvkgvvrllnc> findOne(XarvkgvvrllncQueryRequest query) {
        return repository.findOne(query);
    }
}
