package projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc;

import projectPackage.core.data.Page;
import projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.entity.Xarvkgvvrllnc;
import projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.service.XarvkgvvrllncQuery;

import java.util.Optional;
import java.util.List;

/**
 * 查询服务 模板
 *
 * @author authorxRQXP
 * @date datePlaceholder
 */
public interface XarvkgvvrllncQueryService {

    Xarvkgvvrllnc findById(Keya2Akk5iV3n id);

    List<Xarvkgvvrllnc> findAll();

    List<Xarvkgvvrllnc> findAll(XarvkgvvrllncQuery query);

    Page<Xarvkgvvrllnc> page(XarvkgvvrllncQuery query);


    /**
     * 根据条件查询单个
     *
     * @param query
     * @return
     */
    Optional<Xarvkgvvrllnc> findOne(XarvkgvvrllncQuery query);


}
