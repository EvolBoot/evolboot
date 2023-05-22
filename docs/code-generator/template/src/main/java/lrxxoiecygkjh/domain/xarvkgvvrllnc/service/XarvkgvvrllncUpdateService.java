package projectPackage.lrxxoiecygkjh.domain.service.xarvkgvvrllnc;


import org.springframework.stereotype.Service;
import projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.repository.XarvkgvvrllncRepository;
import projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.entity.Xarvkgvvrllnc;
import projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.service.XarvkgvvrllncRequestBase;

import java.util.Objects;

import lombok.extern.slf4j.Slf4j;

/**
 * 模板
 *
 * @author authorxRQXP
 * @date datePlaceholder
 */
@Slf4j
@Service
public class XarvkgvvrllncUpdateService extends XarvkgvvrllncSupportService {
    protected XarvkgvvrllncUpdateService(XarvkgvvrllncRepository repository) {
        super(repository);
    }

    public void execute(Keya2Akk5iV3n id, Request request) {
        Xarvkgvvrllnc instantiationObjectName = findById(id);
        repository.save(instantiationObjectName);
    }

    public static class Request extends XarvkgvvrllncRequestBase {
    }

}
