package org.evolboot.identity.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.evolboot.core.data.Direction;
import org.evolboot.core.entity.DelState;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.identity.domain.user.entity.UserType;
import org.evolboot.shared.lang.CurrentPrincipal;
import org.evolboot.shared.lang.UserIdentity;

/**
 * 用户批量查询请求
 *
 * @author evol
 */
@Setter
@Getter
public class UserBatchQueryRequest {

    @Schema(title = "页数")
    private Integer page = 1;

    @Schema(title = "每页数量")
    private Integer limit = 20;

    @Schema(title = "用户ID")
    private Long userId;

    @Schema(title = "用户名")
    private String username;

    @Schema(title = "手机号")
    private String mobile;

    @Schema(title = "邮箱")
    private String email;

    @Schema(title = "注册IP")
    private String registerIp;

    @Schema(title = "删除状态")
    private DelState delState;

    @Schema(title = "邀请人ID")
    private Long inviterUserId;

    @Schema(title = "用户类型")
    private UserType userType;

    @Schema(title = "用户身份")
    private UserIdentity userIdentity;

    @Schema(title = "角色ID")
    private Long roleId;

    @Schema(title = "关键字")
    private String key;

    @Schema(title = "排序字段")
    private String sortField;

    @Schema(title = "排序方向")
    private Direction direction;

    @Schema(title = "租户ID")
    private Long tenantId;

    /**
     * 转换为标准查询请求
     */
    public UserQueryRequest convert(Long tenantId) {
        return UserQueryRequest.builder()
                .page(page)
                .limit(limit)
                .userId(userId)
                .username(username)
                .mobile(mobile)
                .email(email)
                .registerIp(registerIp)
                .delState(delState)
                .inviterUserId(inviterUserId)
                .userType(userType)
                .userIdentity(userIdentity)
                .roleId(roleId)
                .key(key)
                .sortField(sortField)
                .direction(direction)
                .tenantId(tenantId)
                .build();
    }

    /**
     * 使用 CurrentPrincipal 转换为标准查询请求
     */
    public UserQueryRequest convert(CurrentPrincipal currentPrincipal) {
        if (ExtendObjects.isNull(currentPrincipal)) {
            return convert((Long) null);
        }
        return convert(currentPrincipal.getTenantId());
    }
}
