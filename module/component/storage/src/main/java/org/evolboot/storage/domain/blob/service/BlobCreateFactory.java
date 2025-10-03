package org.evolboot.storage.domain.blob.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.storage.domain.blob.StorageBoldException;
import org.evolboot.storage.domain.blob.entity.Blob;
import org.evolboot.storage.domain.blob.adapter.StorageSystem;
import org.evolboot.storage.domain.blob.intercept.FileLimitInterceptManager;
import org.evolboot.storage.domain.blob.intercept.FileLimitType;
import org.evolboot.storage.domain.blob.repository.BlobRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author evol
 */
@Service
@Slf4j
public class BlobCreateFactory {

    private final StorageSystem storageSystem;
    private final BlobRepository blobRepository;
    private final FileLimitInterceptManager fileLimitInterceptManager;

    public BlobCreateFactory(StorageSystem storageSystem, BlobRepository blobRepository, FileLimitInterceptManager fileLimitInterceptManager) {
        this.storageSystem = storageSystem;
        this.blobRepository = blobRepository;
        this.fileLimitInterceptManager = fileLimitInterceptManager;
    }

    public Blob create(Request request) {
        fileLimitInterceptManager.allow(request.originalName, request.fileSize, request.type);
        try (
                Blob blob = Blob.builder()
                        .originalName(request.originalName)
                        .inputStream(request.is)
                        .ownerUserId(request.ownerUserId)
                        .build()
        ) {
            StorageSystem.Response response = storageSystem.storeBlob(blob);
            blob.update(
                    response.getUrl(),
                    response.getPath(),
                    response.getName(),
                    response.getStorageType(),
                    request.getType()
            );
            blobRepository.save(blob);
            return blob;
        } catch (IOException e) {
            throw new StorageBoldException(e.getMessage());
        }
    }


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {
        private InputStream is;
        private String originalName;
        private long fileSize;
        private FileLimitType type;
        private Long ownerUserId;
    }
}
