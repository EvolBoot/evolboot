package projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.service;


import org.springframework.stereotype.Service;
import projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.repository.XarvkgvvrllncRepository;
import projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.entity.Xarvkgvvrllnc;
import projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.dto.XarvkgvvrllncRequestBase;
import lombok.extern.slf4j.Slf4j;
import lombok.Getter;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;
/**
 * 模板
 *
 * @author authorxRQXP
 * @date datePlaceholder
 */
@Slf4j
@Service
public class XarvkgvvrllncCreateFactory {

    private final XarvkgvvrllncRepository repository;
    private final XarvkgvvrllncSupportService supportService;

    protected XarvkgvvrllncCreateFactory(XarvkgvvrllncRepository repository, XarvkgvvrllncSupportService supportService) {
        this.repository = repository;
        this.supportService = supportService;
    }

    public Xarvkgvvrllnc execute(Long userId, Request request) {
        Xarvkgvvrllnc instantiationObjectName = new Xarvkgvvrllnc("test");
        repository.save(instantiationObjectName);
        return instantiationObjectName;
    }

    @Setter
    @Getter
    @Schema(title = "创建模板请求")
    public static class Request extends XarvkgvvrllncRequestBase {

    }

}
