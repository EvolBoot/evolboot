package org.evolboot.storage.remote;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.annotation.ApiClient;
import org.evolboot.core.remote.ResponseModel;
import org.evolboot.security.api.SecurityAccessTokenHolder;
import org.evolboot.security.api.annotation.Authenticated;
import org.evolboot.storage.domain.blob.BlobAppService;
import org.evolboot.storage.domain.blob.intercept.FileLimitType;
import org.evolboot.storage.remote.dto.BlobResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/v1/api/storage/blob")
@Tag(name = "文件服务", description = "文件服务")
@Slf4j
@ApiClient
public class AppBlobResourceV1 {

    private final BlobAppService service;

    public AppBlobResourceV1(BlobAppService service) {
        this.service = service;
    }


    @PostMapping(path = "/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "上传图片")
    @Authenticated
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

}
