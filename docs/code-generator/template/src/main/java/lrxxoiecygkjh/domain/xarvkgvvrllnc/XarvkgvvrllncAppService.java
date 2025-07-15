package projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc;

import projectPackage.core.data.Page;
import projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.entity.Xarvkgvvrllnc;
import projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.dto.XarvkgvvrllncQueryRequest;
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
    Xarvkgvvrllnc create(Long userId, XarvkgvvrllncCreateFactory.Request request);

    /**
     * 更新
     *
     * @param request
     */
    void update(Long userId, XarvkgvvrllncUpdateService.Request request);

    /**
     * 删除
     *
     * @param id
     */
    void delete(Long userId, Keya2Akk5iV3n id);

    /**
     * 批量删除
     */
    void deleteAllByIdInBatch(Iterable<Keya2Akk5iV3n> ids);

    /**
     * 批量删除
     * @param request
     */
    void delete(XarvkgvvrllncQueryRequest request);

}
