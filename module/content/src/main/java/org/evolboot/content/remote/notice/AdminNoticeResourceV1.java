package org.evolboot.content.remote.notice;

import org.evolboot.content.domain.notice.Notice;
import org.evolboot.content.domain.notice.NoticeAppService;
import org.evolboot.content.domain.notice.NoticeQuery;
import org.evolboot.core.annotation.AdminClient;
import org.evolboot.core.annotation.OperationLog;
import org.evolboot.core.data.Page;
import org.evolboot.core.remote.DomainId;
import org.evolboot.core.remote.ResponseModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.evolboot.content.ContentAccessAuthorities.Notice.*;
import static org.evolboot.security.api.access.AccessAuthorities.HAS_ROLE_ADMIN;
import static org.evolboot.security.api.access.AccessAuthorities.or;

/**
 * 公告
 *
 * @author evol
 * 
 */
@Slf4j
@RestController
@RequestMapping("/v1/admin/content")
@Tag(name = "公告", description = "公告")
@AdminClient
public class AdminNoticeResourceV1 {

    private final NoticeAppService service;

    public AdminNoticeResourceV1(NoticeAppService service) {
        this.service = service;
    }


    @Operation(summary = "创建公告")
    @OperationLog("创建公告")
    @PostMapping("/notice")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_CREATE)
    public ResponseModel<?> create(
            @RequestBody @Valid
                    NoticeCreateRequest request
    ) {
        Notice notice = service.create(request);
        return ResponseModel.ok(new DomainId(notice.id()));
    }


    @Operation(summary = "删除公告")
    @OperationLog("删除公告")
    @DeleteMapping("/notice/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_DELETE)
    public ResponseModel<?> delete(
            @PathVariable("id") Long id
    ) {
        service.delete(id);
        return ResponseModel.ok();
    }


    @Operation(summary = "修改公告")
    @OperationLog("修改公告")
    @PutMapping("/notice/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_UPDATE)
    public ResponseModel<?> update(
            @PathVariable("id") Long id,
            @RequestBody @Valid
                    NoticeUpdateRequest request
    ) {
        service.update(id, request);
        return ResponseModel.ok();
    }

    @Operation(summary = "查询公告")
    @OperationLog("查询公告")
    @GetMapping("/notice")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_PAGE)
    public ResponseModel<Page<Notice>> page(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "limit", defaultValue = "20") Integer limit
    ) {
        NoticeQuery query = NoticeQuery
                .builder()
                .page(page)
                .limit(limit)
                .build();
        Page<Notice> response = service.page(query);
        return ResponseModel.ok(response);
    }


    @Operation(summary = "查询单个公告")
    @OperationLog("查询单个公告")
    @GetMapping("/notice/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN + or + HAS_SINGLE)
    public ResponseModel<Notice> get(
            @PathVariable("id") Long id
    ) {
        return ResponseModel.ok(service.findById(id));
    }

}
