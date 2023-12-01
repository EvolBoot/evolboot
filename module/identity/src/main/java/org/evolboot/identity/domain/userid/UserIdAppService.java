package org.evolboot.identity.domain.userid;

import org.evolboot.core.data.Page;
import org.evolboot.identity.domain.userid.entity.UserId;
import org.evolboot.identity.domain.userid.dto.UserIdQueryRequest;
import org.evolboot.identity.domain.userid.service.UserIdUpdateService;

import java.util.List;

/**
 * UserId
 *
 * @author evol
 */
public interface UserIdAppService {

    UserId findById(Long id);

    void create(int generateNum);

    void delete(Long id);

    List<UserId> findAll();

    List<UserId> findAll(UserIdQueryRequest query);

    Page<UserId> page(UserIdQueryRequest query);

    Long getNextUserId();

    void use(Long userId);

    /**
     * 检查，内存中的数量不够就增加到内存
     */
    void checkAndAddCache();

    /**
     * 检测，如果低于指定数量就更新
     */
    void checkAndAdd();


}
