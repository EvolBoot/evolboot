package org.evolboot.im.domain.group;

import org.evolboot.core.data.Page;
import org.evolboot.im.domain.group.service.GroupCreateFactory;
import org.evolboot.im.domain.group.service.GroupUpdateService;

import java.util.List;
import java.util.Optional;

/**
 * 群组
 *
 * @author evol
 * @date 2023-05-03 15:52:47
 */
public interface GroupAppService {

    Group findById(Long id);

    /**
     * 创建群
     *
     * @param request
     * @return
     */
    Group create(GroupCreateFactory.Request request);

    void update(Long id, GroupUpdateService.Request request);

    void delete(Long id);

    List<Group> findAll();

    List<Group> findAll(GroupQuery query);

    Page<Group> page(GroupQuery query);


    /**
     * 根据条件查询单个
     *
     * @param query
     * @return
     */
    Optional<Group> findOne(GroupQuery query);


    /**
     * TODO
     * 修改群资料
     */

    /**
     * TODO
     * 修改禁言（全员禁言）
     */

    /**
     * TODO
     * 发布公告
     */

    /**
     * TODO
     * 转让群主
     */

    /**
     * TODO
     * 踢除群员
     */

    /**
     * TODO
     * 邀请群员
     */

    /**
     * TODO
     * 审核申请
     */

    /**
     * TODO
     * 修改群状态
     */

    /**
     * TODO
     * 解散群
     */


}
