package org.evolboot.storage.remote;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.annotation.AdminClient;
import org.evolboot.core.annotation.OperationLog;
import org.evolboot.core.data.Direction;
import org.evolboot.core.data.Page;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.security.api.SecurityAccessTokenHolder;
import org.evolboot.storage.domain.blob.entity.Blob;
import org.evolboot.storage.domain.blob.BlobAppService;
import org.evolboot.storage.domain.blob.dto.AdminBlobPageRequest;
import org.evolboot.storage.domain.blob.entity.BlobType;
import org.evolboot.storage.domain.blob.entity.StorageType;
import org.evolboot.storage.domain.blob.intercept.FileLimitType;
import org.evolboot.storage.remote.dto.BlobResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

import static org.evolboot.security.api.access.AccessAuthorities.HAS_ROLE_ADMIN;
import static org.evolboot.security.api.access.AccessAuthorities.OR;
import static org.evolboot.storage.StorageAccessAuthorities.Bolb.*;

@RestController
@RequestMapping("/v1/admin/storage/blob")
@Tag(name = "文件服务", description = "文件服务")
@Slf4j
@AdminClient
public class AdminBlobResourceV1 {

    private final BlobAppService service;

    public AdminBlobResourceV1(BlobAppService service) {
        this.service = service;
    }


    @Operation(summary = "查询文件服务")
    @GetMapping("")
    @PreAuthorize(HAS_ROLE_ADMIN + OR + HAS_PAGE)
    public ResponseModel<Page<Blob>> page(
            @Parameter(description = "页码") @RequestParam(name = "page", defaultValue = "1") Integer page,
            @Parameter(description = "每页数量") @RequestParam(name = "limit", defaultValue = "20") Integer limit,
            @Parameter(description = "排序字段") @RequestParam(required = false) String sortField,
            @Parameter(description = "排序方向") @RequestParam(required = false) Direction direction,
            @Parameter(description = "文件ID") @RequestParam(required = false) Long id,
            @Parameter(description = "文件名") @RequestParam(required = false) String name,
            @Parameter(description = "原始文件名") @RequestParam(required = false) String originalName,
            @Parameter(description = "文件扩展名") @RequestParam(required = false) String extension,
            @Parameter(description = "文件类型") @RequestParam(required = false) BlobType type,
            @Parameter(description = "存储类型") @RequestParam(required = false) StorageType storageType,
            @Parameter(description = "存储类型") @RequestParam(required = false) FileLimitType fileType,
            @Parameter(description = "所有者用户ID") @RequestParam(required = false) Long ownerUserId,
            @Parameter(description = "创建时间起始") @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date createAtStart,
            @Parameter(description = "创建时间结束") @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date createAtEnd,
            @Parameter(description = "最小文件大小") @RequestParam(required = false) Long minSize,
            @Parameter(description = "最大文件大小") @RequestParam(required = false) Long maxSize
    ) {
        AdminBlobPageRequest request = AdminBlobPageRequest.builder()
                .page(page)
                .limit(limit)
                .sortField(sortField)
                .direction(direction)
                .id(id)
                .name(name)
                .originalName(originalName)
                .extension(extension)
                .type(type)
                .storageType(storageType)
                .ownerUserId(ownerUserId)
                .createAtStart(createAtStart)
                .createAtEnd(createAtEnd)
                .minSize(minSize)
                .maxSize(maxSize)
                .fileType(fileType)
                .build();

        Page<Blob> response = service.page(request.toBlobQueryRequest());
        return ResponseModel.ok(response);
    }


    @PostMapping(path = "/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "上传图片")
    @PreAuthorize(HAS_ROLE_ADMIN)

    public ResponseModel<BlobResponse> upload(
            @RequestPart("file") MultipartFile uploadFile
    ) {
        String fileUrl = "";
        try {
            long size = uploadFile.getSize();
            fileUrl = service.create(uploadFile.getInputStream(), uploadFile.getOriginalFilename(), size, FileLimitType.IMAGE, SecurityAccessTokenHolder.getPrincipalId()).getUrl();
        } catch (IOException e) {
            log.error("上传图片异常", e);
        }
        return ResponseModel.ok(new BlobResponse(fileUrl));
    }


    @PostMapping(path = "/video", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "上传视频")
    @PreAuthorize(HAS_ROLE_ADMIN + OR + HAS_CREATE)

    public ResponseModel<BlobResponse> uploadVideo(
            @RequestPart("file") MultipartFile uploadFile
    ) {
        String fileUrl = "";
        try {
            long size = uploadFile.getSize();
            fileUrl = service.create(uploadFile.getInputStream(), uploadFile.getOriginalFilename(), size, FileLimitType.VIDEO, SecurityAccessTokenHolder.getPrincipalId()).getUrl();
        } catch (IOException e) {
            log.error("上传视频异常", e);
        }
        return ResponseModel.ok(new BlobResponse(fileUrl));
    }


    @PostMapping(path = "/document", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "上传文档")
    @PreAuthorize(HAS_ROLE_ADMIN + OR + HAS_CREATE)
    public ResponseModel<BlobResponse> uploadDocument(
            @RequestPart("file") MultipartFile uploadFile
    ) {
        String fileUrl = "";
        try {
            long size = uploadFile.getSize();
            fileUrl = service.create(uploadFile.getInputStream(), uploadFile.getOriginalFilename(), size, FileLimitType.DOCUMENT, SecurityAccessTokenHolder.getPrincipalId()).getUrl();
        } catch (IOException e) {
            log.error("上传文档异常", e);
        }
        return ResponseModel.ok(new BlobResponse(fileUrl));
    }


    @PostMapping(path = "/app", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "上传APP")
    @PreAuthorize(HAS_ROLE_ADMIN + OR + HAS_CREATE)
    public ResponseModel<BlobResponse> uploadApp(
            @RequestPart("file") MultipartFile uploadFile
    ) {
        String fileUrl = "";
        try {
            long size = uploadFile.getSize();
            fileUrl = service.create(uploadFile.getInputStream(), uploadFile.getOriginalFilename(), size, FileLimitType.APP, SecurityAccessTokenHolder.getPrincipalId()).getUrl();
        } catch (IOException e) {
            log.error("上传APP异常", e);
        }
        return ResponseModel.ok(new BlobResponse(fileUrl));
    }

    @Operation(summary = "删除文件")
    @OperationLog("删除文件")
    @DeleteMapping("/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN + OR + HAS_DELETE)
    public ResponseModel<?> delete(
            @PathVariable("id") Long id
    ) {
        service.delete(id);
        return ResponseModel.ok();
    }


}
