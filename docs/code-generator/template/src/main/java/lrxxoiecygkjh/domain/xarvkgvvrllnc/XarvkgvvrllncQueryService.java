package projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc;

import projectPackage.core.data.Page;
import projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.entity.Xarvkgvvrllnc;
import projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.dto.XarvkgvvrllncQueryRequest;

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

    boolean existsById(Keya2Akk5iV3n id);

    List<Xarvkgvvrllnc> findAll(XarvkgvvrllncQueryRequest query);

    Page<Xarvkgvvrllnc> page(XarvkgvvrllncQueryRequest query);


    /**
     * 根据条件查询单个
     *
     * @param query
     * @return
     */
    Optional<Xarvkgvvrllnc> findOne(XarvkgvvrllncQueryRequest query);


}
