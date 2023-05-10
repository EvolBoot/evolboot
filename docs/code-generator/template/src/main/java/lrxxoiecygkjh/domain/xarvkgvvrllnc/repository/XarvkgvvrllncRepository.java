package projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.repository;

import projectPackage.core.data.Page;
import projectPackage.core.data.Sort;
import projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.Xarvkgvvrllnc;
import projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.Xarvkgvvrllnc;
import projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.XarvkgvvrllncQuery;


import java.util.List;
import java.util.Optional;

/**
 * 模板
 *
 * @author authorxRQXP
 * @date datePlaceholder
 */
public interface XarvkgvvrllncRepository {

    Xarvkgvvrllnc save(Xarvkgvvrllnc instantiationObjectName);

    Optional<Xarvkgvvrllnc> findById(Keya2Akk5iV3n id);

    Page<Xarvkgvvrllnc> page(XarvkgvvrllncQuery query);

    void deleteById(Keya2Akk5iV3n id);

    List<Xarvkgvvrllnc> findAll();

    List<Xarvkgvvrllnc> findAll(XarvkgvvrllncQuery query);

    /**
     * 根据条件查询单个
     * @param query
     * @return
     */
    Optional<Xarvkgvvrllnc> findOne(XarvkgvvrllncQuery query);

}
