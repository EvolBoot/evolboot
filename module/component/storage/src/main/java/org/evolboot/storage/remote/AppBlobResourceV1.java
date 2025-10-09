package org.evolboot.storage.remote;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.annotation.ApiClient;
import org.evolboot.core.annotation.OperationLog;
import org.evolboot.core.data.Direction;
import org.evolboot.core.data.Page;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.security.api.SecurityAccessTokenHolder;
import org.evolboot.security.api.annotation.Authenticated;
import org.evolboot.shared.lang.OwnerType;
import org.evolboot.shared.resource.ResourceOwner;
import org.evolboot.storage.domain.blob.BlobAppService;
import org.evolboot.storage.domain.blob.dto.BlobQueryRequest;
import org.evolboot.storage.domain.blob.entity.Blob;
import org.evolboot.storage.domain.blob.entity.BlobType;
import org.evolboot.storage.domain.blob.entity.StorageType;
import org.evolboot.storage.domain.blob.intercept.FileLimitType;
import org.evolboot.storage.remote.dto.BlobResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

/**
 * App Blob Resource
 * C端用户文件管理 - 登录用户可访问
 * 只能管理个人文件，数据自动隔离
 */
@RestController
@RequestMapping("/v1/app/storage/blob")
@Tag(name = "用户文件管理", description = "用户文件管理")
@Slf4j
@ApiClient
public class AppBlobResourceV1 {

    private final BlobAppService service;

    public AppBlobResourceV1(BlobAppService service) {
        this.service = service;
    }

    @Operation(summary = "查询个人文件")
    @GetMapping("")
    @Authenticated
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
            @Parameter(description = "文件限制类型") @RequestParam(required = false) FileLimitType fileType,
            @Parameter(description = "创建时间起始") @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date createAtStart,
            @Parameter(description = "创建时间结束") @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date createAtEnd,
            @Parameter(description = "最小文件大小") @RequestParam(required = false) Long minSize,
            @Parameter(description = "最大文件大小") @RequestParam(required = false) Long maxSize
    ) {
        // 自动获取当前用户ID，强制数据隔离
        Long userId = SecurityAccessTokenHolder.getUserId();

        BlobQueryRequest request = BlobQueryRequest.builder()
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
                .ownerType(OwnerType.USER)  // 强制只查询个人文件
                .ownerId(userId)  // 强制只查询自己的文件
                .createAtStart(createAtStart)
                .createAtEnd(createAtEnd)
                .minSize(minSize)
                .maxSize(maxSize)
                .fileType(fileType)
                .build();

        Page<Blob> response = service.page(request);
        return ResponseModel.ok(response);
    }

    @PostMapping(path = "/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "上传图片")
    @Authenticated
    public ResponseModel<BlobResponse> uploadImage(
            @RequestPart("file") MultipartFile uploadFile
    ) {
        String fileUrl = "";
        try {
            long size = uploadFile.getSize();
            Long currentUserId = SecurityAccessTokenHolder.getUserId();
            fileUrl = service.create(
                    uploadFile.getInputStream(),
                    uploadFile.getOriginalFilename(),
                    size,
                    FileLimitType.IMAGE,
                    ResourceOwner.user(currentUserId),
                    currentUserId
            ).getUrl();
        } catch (IOException e) {
            log.error("上传图片异常", e);
        }
        return ResponseModel.ok(new BlobResponse(fileUrl));
    }

    @PostMapping(path = "/video", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "上传视频")
    @Authenticated
    public ResponseModel<BlobResponse> uploadVideo(
            @RequestPart("file") MultipartFile uploadFile
    ) {
        String fileUrl = "";
        try {
            long size = uploadFile.getSize();
            Long currentUserId = SecurityAccessTokenHolder.getUserId();
            fileUrl = service.create(
                    uploadFile.getInputStream(),
                    uploadFile.getOriginalFilename(),
                    size,
                    FileLimitType.VIDEO,
                    ResourceOwner.user(currentUserId),
                    currentUserId
            ).getUrl();
        } catch (IOException e) {
            log.error("上传视频异常", e);
        }
        return ResponseModel.ok(new BlobResponse(fileUrl));
    }

    @PostMapping(path = "/document", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "上传文档")
    @Authenticated
    public ResponseModel<BlobResponse> uploadDocument(
            @RequestPart("file") MultipartFile uploadFile
    ) {
        String fileUrl = "";
        try {
            long size = uploadFile.getSize();
            Long currentUserId = SecurityAccessTokenHolder.getUserId();
            fileUrl = service.create(
                    uploadFile.getInputStream(),
                    uploadFile.getOriginalFilename(),
                    size,
                    FileLimitType.DOCUMENT,
                    ResourceOwner.user(currentUserId),
                    currentUserId
            ).getUrl();
        } catch (IOException e) {
            log.error("上传文档异常", e);
        }
        return ResponseModel.ok(new BlobResponse(fileUrl));
    }

    @Operation(summary = "删除文件")
    @OperationLog("删除文件")
    @DeleteMapping("/{id}")
    @Authenticated
    public ResponseModel<?> delete(
            @PathVariable("id") Long id
    ) {
        service.delete(id);
        return ResponseModel.ok();
    }

}
