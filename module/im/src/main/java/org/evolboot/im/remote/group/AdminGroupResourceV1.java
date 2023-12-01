package org.evolboot.im.remote.group;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.annotation.AdminClient;
import org.evolboot.core.annotation.OperationLog;
import org.evolboot.core.data.Page;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.im.domain.group.GroupQueryService;
import org.evolboot.im.domain.group.entity.Group;
import org.evolboot.im.domain.group.GroupAppService;
import org.evolboot.im.domain.group.dto.GroupQueryRequest;
import org.evolboot.im.remote.group.dto.GroupUpdateRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.Date;

import static org.evolboot.im.ImAccessAuthorities.Group.*;
import static org.evolboot.security.api.access.AccessAuthorities.HAS_ROLE_ADMIN;
import static org.evolboot.security.api.access.AccessAuthorities.OR;

/**
 * 群组
 *
 * @author evol
 * @date 2023-05-03 15:52:47
 */
@Slf4j
@RestController
@RequestMapping("/v1/admin/im/group")
@Tag(name = "群组", description = "群组")
@AdminClient
public class AdminGroupResourceV1 {

    private final GroupAppService service;
    private final GroupQueryService queryService;


    public AdminGroupResourceV1(GroupAppService service, GroupQueryService queryService) {
        this.service = service;
        this.queryService = queryService;
    }

    /*

        @Operation(summary = "创建群组")
        @OperationLog("创建群组")
        @PostMapping("")
        @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_CREATE)
        public ResponseModel<?> create(
                @RequestBody @Valid
                        GroupCreateRequest request
        ) {
            Group group = service.create(request);
            return ResponseModel.ok(new DomainId(group.id()));
        }



        @Operation(summary = "删除群组")
        @OperationLog("删除群组")
        @DeleteMapping("/{id}")
        @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_DELETE)
        public ResponseModel<?> delete(
                @PathVariable("id") Long id
        ) {
            service.delete(id);
            return ResponseModel.ok();
        }




     */

    @Operation(summary = "修改群组")
    @OperationLog("修改群组")
    @PutMapping
    @PreAuthorize(HAS_ROLE_ADMIN + OR + HAS_UPDATE)
    public ResponseModel<?> update(
            @RequestBody @Valid
            GroupUpdateRequest request
    ) {
        service.update(request);
        return ResponseModel.ok();
    }


    @Operation(summary = "查询群组")
    @GetMapping("")
    @PreAuthorize(HAS_ROLE_ADMIN + OR + HAS_PAGE)
    public ResponseModel<Page<Group>> page(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "limit", defaultValue = "20") Integer limit,
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) Date startDate,
            @RequestParam(required = false) Date endDate
    ) {
        GroupQueryRequest query = GroupQueryRequest
                .builder()
                .id(id)
                .startDate(startDate)
                .endDate(endDate)
                .page(page)
                .limit(limit)
                .build();
        Page<Group> response = queryService.page(query);
        return ResponseModel.ok(response);
    }


    @Operation(summary = "查询单个群组")
    @GetMapping("/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN + OR + HAS_SINGLE)
    public ResponseModel<Group> get(
            @PathVariable("id") Long id
    ) {
        return ResponseModel.ok(queryService.findById(id));
    }

}
