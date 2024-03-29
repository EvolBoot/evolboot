package org.evolboot.im.domain.group;

import org.evolboot.im.domain.group.entity.Group;
import org.evolboot.im.domain.group.service.GroupCreateFactory;
import org.evolboot.im.domain.group.service.GroupUpdateService;

/**
 * 群组
 *
 * @author evol
 * @date 2023-05-03 15:52:47
 */
public interface GroupAppService {

    /**
     * 创建群
     *
     * @param request
     * @return
     */
    Group create(GroupCreateFactory.Request request);

    void update(GroupUpdateService.Request request);

    void delete(Long id);


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
