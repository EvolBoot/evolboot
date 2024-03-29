package org.evolboot.im.remote.group.dto;


import lombok.Getter;
import lombok.Setter;
import org.evolboot.im.domain.group.service.GroupCreateFactory;

/**
 * 群组
 *
 * @author evol
 * @date 2023-05-03 15:52:47
 */

@Setter
@Getter
public class GroupCreateRequest {


    private String name;

    private String avatar;

    private String description;


    public GroupCreateFactory.Request to(Long ownerUserId) {
        return new GroupCreateFactory.Request(
                ownerUserId, name, avatar, description
        );
    }

}
