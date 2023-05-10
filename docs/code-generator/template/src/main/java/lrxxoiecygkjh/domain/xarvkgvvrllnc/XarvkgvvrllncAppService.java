package projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc;

import projectPackage.core.data.Page;
import org.springframework.transaction.annotation.Transactional;
import projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.service.XarvkgvvrllncCreateFactory;
import projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.service.XarvkgvvrllncUpdateService;

import java.util.List;

/**
 * 模板
 *
 * @author authorxRQXP
 * @date datePlaceholder
 */
public interface XarvkgvvrllncAppService {

    Xarvkgvvrllnc findById(Keya2Akk5iV3n id);

    Xarvkgvvrllnc create(XarvkgvvrllncCreateFactory.Request request);

    void update(Keya2Akk5iV3n id, XarvkgvvrllncUpdateService.Request request);

    void delete(Keya2Akk5iV3n id);

    List<Xarvkgvvrllnc> findAll();

    List<Xarvkgvvrllnc> findAll(XarvkgvvrllncQuery query);

    Page<Xarvkgvvrllnc> page(XarvkgvvrllncQuery query);


    /**
     * 根据条件查询单个
     * @param query
     * @return
     */
    Optional<Xarvkgvvrllnc> findOne(XarvkgvvrllncQuery query);



}
