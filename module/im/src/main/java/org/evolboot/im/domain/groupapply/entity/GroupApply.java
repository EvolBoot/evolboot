package org.evolboot.im.domain.groupapply.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.data.jpa.JpaAbstractEntity;
import org.evolboot.core.entity.AggregateRoot;
import org.evolboot.core.entity.IdGenerate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;


/**
 * 群申请
 *
 * @author evol
 * @date 2023-05-03 17:27:04
 */
@Table(name = "evoltb_im_group_apply")
@Getter
@Slf4j
@NoArgsConstructor
@Entity
@Schema(title = "群申请")
public class GroupApply extends JpaAbstractEntity<Long> implements AggregateRoot<GroupApply> {

    @Id
    private Long id;

    /**
     * 申请的群
     */
    @Schema(title = "申请的群")
    private Long groupId;

    /**
     * 申请用户
     */
    @Schema(title = "申请用户")
    private Long applyUserId;

    /**
     * 申请原因
     */
    @Schema(title = "申请原因")
    private String applyReason;

    /**
     * 状态
     */
    @Schema(title = "状态")
    private GroupApplyState state;

    /**
     * 未处理到期时间
     */
    @Schema(title = "未处理到期时间")
    private Date expireAt;

    /**
     * 处理人
     */
    @Schema(title = "处理人")
    private Long handleUserId;

    /**
     * 处理时间
     */
    @Schema(title = "处理时间")
    private Date handleAt;


    private void generateId() {
        this.id = IdGenerate.longId();
    }


    public GroupApply(String name) {
        //   setLocales(locales);
        generateId();
    }

    @Override
    public Long id() {
        return id;
    }

    @Override
    public GroupApply root() {
        return this;
    }
}
