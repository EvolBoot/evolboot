package org.evolboot.bff.domain.admin;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.bff.domain.admin.repository.BffAdminMapper;
import org.evolboot.bff.domain.admin.response.BffUser;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.springframework.stereotype.Service;

/**
 * @author evol
 */
@Service
@Slf4j
public class BffAdminService {

    private final BffAdminMapper mapper;

    public BffAdminService(BffAdminMapper mapper) {
        this.mapper = mapper;
    }


    public Page<BffUser> findUser(BffAdminQuery query) {
        return PageImpl.of(mapper.findUser(query.toMybatisPage(), query));
    }

}
