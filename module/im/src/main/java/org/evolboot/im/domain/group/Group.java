package org.evolboot.im.domain.group;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.evolboot.core.data.jpa.JpaAbstractEntity;
import org.evolboot.core.domain.AggregateRoot;
import org.evolboot.core.domain.IdGenerate;
import org.evolboot.im.domain.group.repository.jpa.convert.GroupLocaleListConverter;
import org.evolboot.core.domain.LocaleDomainPart;
import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;
import java.util.List;


/**
 * 群组
 *
 * @author evol
 * @date 2023-05-03 15:52:47
 */
@Table(name = "evol_im_group")
@Getter
@Slf4j
@NoArgsConstructor
@Entity
public class Group extends JpaAbstractEntity<Long> implements AggregateRoot<Group> {

    @Id
    private Long id;

    /**
     * 群主
     */
    private Long ownerUserId;

    /**
     * 群名称
     */
    private String name;

    /**
     * 群头像
     */
    private String avatar;

    /**
     * 群描述
     */
    private String description;

    /**
     * 会话ID
     */
    private Long conversationId;

    /**
     * 群通知
     */
    private String notification;

    /**
     * 群状态
     */
    private GroupStatus status = GroupStatus.NEED_APPLY;

    /**
     * 限制人数
     */
    private Short limit = 500;

    /**
     * 群通知
     */
    private String remark;

    private void generateId() {
        this.id = IdGenerate.longId();
    }


    public Group(String name) {
        //   setLocales(locales);
        generateId();
    }

    @Override
    public Long id() {
        return id;
    }

    @Override
    public Group root() {
        return this;
    }
}
