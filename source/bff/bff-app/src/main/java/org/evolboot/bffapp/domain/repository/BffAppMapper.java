package org.evolboot.bffapp.domain.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.evolboot.bffapp.domain.BffAppQuery;
import org.evolboot.bffapp.domain.BffUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author evol
 *
 */
@Mapper
public interface BffAppMapper {

    IPage<BffUser> findUser(Page<BffUser> page, @Param("query") BffAppQuery query);

}
