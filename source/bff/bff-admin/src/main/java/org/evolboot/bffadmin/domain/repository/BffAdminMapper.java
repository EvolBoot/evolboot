package org.evolboot.bffadmin.domain.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.evolboot.bffadmin.domain.BffAdminQuery;
import org.evolboot.bffadmin.domain.response.BffStaffUser;
import org.evolboot.bffadmin.domain.response.BffUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author evol
 *
 */
@Mapper
public interface BffAdminMapper {

    /**
     * 示例
     *
     * @param page
     * @param query
     * @return
     */
    IPage<BffUser> findUser(Page<BffUser> page, @Param("query") BffAdminQuery query);

    /**
     * 查找员工列表
     *
     * @param page
     * @param query
     * @return
     */
    IPage<BffStaffUser> findStaffUser(Page<BffAdminQuery> page, BffAdminQuery query);
}
