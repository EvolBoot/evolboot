package org.evolboot.system.domain.userloginlog.repository;

import org.evolboot.core.data.Page;
import org.evolboot.system.domain.userloginlog.UserLoginLog;
import org.evolboot.system.domain.userloginlog.UserLoginLogQuery;

/**
 * @author evol
 * 
 */
public interface UserLoginLogRepository {

    UserLoginLog save(UserLoginLog userLoginLog);

    Page<UserLoginLog> page(UserLoginLogQuery query);

}
