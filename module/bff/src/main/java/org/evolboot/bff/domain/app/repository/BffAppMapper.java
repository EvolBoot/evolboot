package org.evolboot.bff.domain.app.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.evolboot.bff.domain.app.dto.BffAppQueryRequest;
import org.evolboot.bff.domain.app.dto.BffUser;

/**
 * @author evol
 */
@Mapper
public interface BffAppMapper {

    IPage<BffUser> findUser(Page<BffUser> page, @Param("query") BffAppQueryRequest query);

}
