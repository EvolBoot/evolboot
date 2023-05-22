package org.evolboot.system.domain.userloginlog;

import org.evolboot.core.data.Page;
import org.evolboot.system.domain.userloginlog.entity.UserLoginLog;
import org.evolboot.system.domain.userloginlog.service.UserLoginLogQuery;

/**
 * @author evol
 */
public interface UserLoginLogAppService {


    Page<UserLoginLog> page(UserLoginLogQuery query);


}
