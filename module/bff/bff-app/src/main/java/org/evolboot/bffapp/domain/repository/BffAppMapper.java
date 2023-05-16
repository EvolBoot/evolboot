package org.evolboot.bffapp.domain.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.evolboot.bffapp.domain.BffAppQuery;
import org.evolboot.bffapp.domain.BffUser;

/**
 * @author evol
 */
@Mapper
public interface BffAppMapper {

    IPage<BffUser> findUser(Page<BffUser> page, @Param("query") BffAppQuery query);

}
