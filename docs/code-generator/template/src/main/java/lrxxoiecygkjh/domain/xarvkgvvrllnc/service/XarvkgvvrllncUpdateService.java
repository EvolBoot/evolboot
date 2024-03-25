package projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.service;


import org.springframework.stereotype.Service;
import projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.repository.XarvkgvvrllncRepository;
import projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.entity.Xarvkgvvrllnc;
import projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.dto.XarvkgvvrllncRequestBase;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

/**
 * 模板
 *
 * @author authorxRQXP
 * @date datePlaceholder
 */
@Slf4j
@Service
public class XarvkgvvrllncUpdateService {

    private final XarvkgvvrllncRepository repository;
    private final XarvkgvvrllncSupportService supportService;

    protected XarvkgvvrllncUpdateService(XarvkgvvrllncRepository repository, XarvkgvvrllncSupportService supportService) {
        this.repository = repository;
        this.supportService = supportService;
    }

    public void execute(Request request) {
        Xarvkgvvrllnc instantiationObjectName = supportService.findById(request.getId());
        repository.save(instantiationObjectName);
    }
    @Getter
    @Setter
    @Schema(title = "更新模板请求")
    public static class Request extends XarvkgvvrllncRequestBase {

        @Schema(title = "模板唯一ID")
        private Keya2Akk5iV3n id;
    }

}
