package org.evolboot.system.domain.userloginlog;

import org.evolboot.core.data.Page;

/**
 * @author evol
 * 
 */
public interface UserLoginLogAppService {


    Page<UserLoginLog> page(UserLoginLogQuery query);


}
