package org.evolboot.storage.remote;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.annotation.AdminClient;
import org.evolboot.core.data.Page;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.security.api.SecurityAccessTokenHolder;
import org.evolboot.storage.domain.blob.entity.Blob;
import org.evolboot.storage.domain.blob.BlobAppService;
import org.evolboot.storage.domain.blob.service.BlobQuery;
import org.evolboot.storage.domain.blob.intercept.FileLimitType;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.evolboot.security.api.access.AccessAuthorities.HAS_ROLE_ADMIN;

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
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ResponseModel<Page<Blob>> page(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "limit", defaultValue = "20") Integer limit
    ) {
        BlobQuery query = BlobQuery
                .builder()
                .page(page)
                .limit(limit)
                .build();
        Page<Blob> response = service.page(query);
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
            fileUrl = service.create(uploadFile.getInputStream(), uploadFile.getOriginalFilename(), size, FileLimitType.IMAGE, SecurityAccessTokenHolder.getPrincipalId());
        } catch (IOException e) {
            log.error("上传图片异常", e);
        }
        return ResponseModel.ok(new BlobResponse(fileUrl));
    }


    @PostMapping(path = "/video", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "上传视频")
    @PreAuthorize(HAS_ROLE_ADMIN)

    public ResponseModel<BlobResponse> uploadVideo(
            @RequestPart("file") MultipartFile uploadFile
    ) {
        String fileUrl = "";
        try {
            long size = uploadFile.getSize();
            fileUrl = service.create(uploadFile.getInputStream(), uploadFile.getOriginalFilename(), size, FileLimitType.VIDEO, SecurityAccessTokenHolder.getPrincipalId());
        } catch (IOException e) {
            log.error("上传视频异常", e);
        }
        return ResponseModel.ok(new BlobResponse(fileUrl));
    }


    @PostMapping(path = "/document", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "上传文档")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ResponseModel<BlobResponse> uploadDocument(
            @RequestPart("file") MultipartFile uploadFile
    ) {
        String fileUrl = "";
        try {
            long size = uploadFile.getSize();
            fileUrl = service.create(uploadFile.getInputStream(), uploadFile.getOriginalFilename(), size, FileLimitType.DOCUMENT, SecurityAccessTokenHolder.getPrincipalId());
        } catch (IOException e) {
            log.error("上传文档异常", e);
        }
        return ResponseModel.ok(new BlobResponse(fileUrl));
    }


    @PostMapping(path = "/app", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "上传APP")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ResponseModel<BlobResponse> uploadApp(
            @RequestPart("file") MultipartFile uploadFile
    ) {
        String fileUrl = "";
        try {
            long size = uploadFile.getSize();
            fileUrl = service.create(uploadFile.getInputStream(), uploadFile.getOriginalFilename(), size, FileLimitType.APP, SecurityAccessTokenHolder.getPrincipalId());
        } catch (IOException e) {
            log.error("上传APP异常", e);
        }
        return ResponseModel.ok(new BlobResponse(fileUrl));
    }
}
