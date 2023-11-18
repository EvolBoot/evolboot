package org.evolboot.system.domain.userloginlog.repository;

import org.evolboot.core.data.BaseRepository;
import org.evolboot.core.data.Page;
import org.evolboot.system.domain.userloginlog.entity.UserLoginLog;
import org.evolboot.system.domain.userloginlog.service.UserLoginLogQuery;

/**
 * @author evol
 */
public interface UserLoginLogRepository extends BaseRepository<UserLoginLog, Long> {

    UserLoginLog save(UserLoginLog userLoginLog);

    Page<UserLoginLog> page(UserLoginLogQuery query);

}
