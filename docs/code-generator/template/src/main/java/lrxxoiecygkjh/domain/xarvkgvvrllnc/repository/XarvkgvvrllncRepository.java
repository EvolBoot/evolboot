package projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.repository;

import projectPackage.core.data.Page;
import projectPackage.core.data.Query;
import projectPackage.core.data.Sort;
import projectPackage.core.data.BaseRepository;
import projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.entity.Xarvkgvvrllnc;
import projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.dto.XarvkgvvrllncQueryRequest;


import java.util.List;
import java.util.Optional;

/**
 * 模板
 *
 * @author authorxRQXP
 * @date datePlaceholder
 */
public interface XarvkgvvrllncRepository extends BaseRepository<Xarvkgvvrllnc, Keya2Akk5iV3n> {

    Xarvkgvvrllnc save(Xarvkgvvrllnc instantiationObjectName);

    Optional<Xarvkgvvrllnc> findById(Keya2Akk5iV3n id);


    boolean existsById(Keya2Akk5iV3n id);

    void deleteById(Keya2Akk5iV3n id);

    List<Xarvkgvvrllnc> findAll();

    <Q extends Query> List<Xarvkgvvrllnc> findAll(Q query);

    <Q extends Query> Optional<Xarvkgvvrllnc> findOne(Q query);

    <Q extends Query> long count(Q query);

    <Q extends Query> Page<Xarvkgvvrllnc> page(Q query);

}
