package projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc;

import projectPackage.core.data.Page;
import projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.entity.Xarvkgvvrllnc;
import projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.service.XarvkgvvrllncQuery;
import projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.service.XarvkgvvrllncCreateFactory;
import projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.service.XarvkgvvrllncUpdateService;

import java.util.Optional;
import java.util.List;

/**
 * 模板
 *
 * @author authorxRQXP
 * @date datePlaceholder
 */
public interface XarvkgvvrllncAppService {

    /**
     * 创建
     *
     * @param request
     * @return
     */
    Xarvkgvvrllnc create(XarvkgvvrllncCreateFactory.Request request);

    /**
     * 更新
     *
     * @param request
     */
    void update(XarvkgvvrllncUpdateService.Request request);

    /**
     * 删除
     *
     * @param id
     */
    void delete(Keya2Akk5iV3n id);

    /**
     * 批量删除
     */
    void deleteAllByIdInBatch(Iterable<Keya2Akk5iV3n> ids);


}
