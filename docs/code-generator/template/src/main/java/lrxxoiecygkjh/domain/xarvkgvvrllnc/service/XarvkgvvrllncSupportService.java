package projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.service;

import projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.entity.Xarvkgvvrllnc;
import projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.repository.XarvkgvvrllncRepository;
import projectPackage.core.exception.DomainNotFoundException;
import projectPackage.core.i18n.I18NMessageHolder;
import projectPackage.lrxxoiecygkjh.LrxxoiecygkjhI18nMessage;
import projectPackage.core.util.Assert;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;


/**
 * 模板
 *
 * @author authorxRQXP
 * @date datePlaceholder
 */
@Slf4j
@Service
public class XarvkgvvrllncSupportService {

    protected final XarvkgvvrllncRepository repository;

    protected XarvkgvvrllncSupportService(XarvkgvvrllncRepository repository) {
        this.repository = repository;
    }

    public Xarvkgvvrllnc findById(Keya2Akk5iV3n id) {
        Assert.notNull(id, "模板的ID不能为空");
        return repository.findById(id).orElseThrow(() -> new DomainNotFoundException(I18NMessageHolder.message(LrxxoiecygkjhI18nMessage.Xarvkgvvrllnc.NOT_FOUND)));
    }

    public boolean existsById(Keya2Akk5iV3n id) {
        return repository.existsById(id);
    }

}
