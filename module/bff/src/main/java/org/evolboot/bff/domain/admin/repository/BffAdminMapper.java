package org.evolboot.bff.domain.admin.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.evolboot.bff.domain.admin.BffAdminQuery;
import org.evolboot.bff.domain.admin.response.BffStaffUser;
import org.evolboot.bff.domain.admin.response.BffUser;

/**
 * @author evol
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
