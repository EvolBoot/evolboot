package projectPackage.lrxxoiecygkjh.domain.service.xarvkgvvrllnc;


import org.springframework.stereotype.Service;
import projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.repository.XarvkgvvrllncRepository;
import projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.Xarvkgvvrllnc;
import projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.
        XarvkgvvrllncRequestBase;
import lombok.extern.slf4j.Slf4j;

/**
 * 模板
 *
 * @author authorxRQXP
 * @date datePlaceholder
 */
@Slf4j
@Service
public class XarvkgvvrllncCreateFactory extends XarvkgvvrllncSupportService {
    protected XarvkgvvrllncCreateFactory(XarvkgvvrllncRepository repository) {
        super(repository);
    }

    public Xarvkgvvrllnc execute(Request request) {
        Xarvkgvvrllnc instantiationObjectName = new Xarvkgvvrllnc("test");
        repository.save(instantiationObjectName);
        return instantiationObjectName;
    }

    public static class Request extends XarvkgvvrllncRequestBase {

    }

}
