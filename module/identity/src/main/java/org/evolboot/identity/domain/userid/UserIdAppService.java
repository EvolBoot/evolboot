package org.evolboot.identity.domain.userid;

import org.evolboot.core.data.Page;
import org.evolboot.identity.domain.userid.entity.UserId;
import org.evolboot.identity.domain.userid.service.UserIdQuery;
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

    void update(Long id, UserIdUpdateService.Request request);

    void delete(Long id);

    List<UserId> findAll();

    List<UserId> findAll(UserIdQuery query);

    Page<UserId> page(UserIdQuery query);

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
