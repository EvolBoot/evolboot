package org.evolboot.system.domain.userloginlog;

import org.evolboot.core.data.Page;
import org.evolboot.system.domain.userloginlog.entity.UserLoginLog;
import org.evolboot.system.domain.userloginlog.dto.UserLoginLogQueryRequest;

/**
 * @author evol
 */
public interface UserLoginLogAppService {


    Page<UserLoginLog> page(UserLoginLogQueryRequest query);


}
