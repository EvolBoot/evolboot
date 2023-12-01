package org.evolboot.bff.domain.app;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.bff.domain.app.dto.BffAppQueryRequest;
import org.evolboot.bff.domain.app.dto.BffUser;
import org.evolboot.bff.domain.app.repository.BffAppMapper;
import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.springframework.stereotype.Service;

/**
 * @author evol
 */
@Service
@Slf4j
public class BffAppService {

    private final BffAppMapper mapper;

    public BffAppService(BffAppMapper mapper) {
        this.mapper = mapper;
    }

    public Page<BffUser> findUser(BffAppQueryRequest query) {
        return PageImpl.of(mapper.findUser(query.toMybatisPage(), query));
    }

}
