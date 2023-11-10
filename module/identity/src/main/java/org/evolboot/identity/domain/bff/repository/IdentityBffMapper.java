package org.evolboot.identity.domain.bff.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.evolboot.identity.domain.bff.dto.BffStaffUser;
import org.evolboot.identity.domain.bff.service.IdentityBffQuery;

/**
 * @author evol
 */
@Mapper
public interface IdentityBffMapper {


    /**
     * 查找员工列表
     *
     * @param page
     * @param query
     * @return
     */
    IPage<BffStaffUser> findStaffUser(Page<IdentityBffQuery> page, IdentityBffQuery query);
}
