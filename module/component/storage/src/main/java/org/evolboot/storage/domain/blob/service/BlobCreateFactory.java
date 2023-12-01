package org.evolboot.storage.domain.blob.service;

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
public class BlobCreateFactory {

    private final StorageSystem storageSystem;
    private final BlobRepository blobRepository;
    private final FileLimitInterceptManager fileLimitInterceptManager;

    public BlobCreateFactory(StorageSystem storageSystem, BlobRepository blobRepository, FileLimitInterceptManager fileLimitInterceptManager) {
        this.storageSystem = storageSystem;
        this.blobRepository = blobRepository;
        this.fileLimitInterceptManager = fileLimitInterceptManager;
    }

    public Blob create(InputStream is, String originalName, long fileSize, FileLimitType type, Long ownerUserId) {
        fileLimitInterceptManager.allow(originalName, fileSize, type);
        try (
                Blob blob = Blob.builder()
                        .originalName(originalName)
                        .inputStream(is)
                        .ownerUserId(ownerUserId)
                        .build()
        ) {
            StorageSystem.Response response = storageSystem.storeBlob(blob);
            blob.update(
                    response.getUrl(),
                    response.getPath(),
                    response.getName(),
                    response.getStorageType()
            );
            blobRepository.save(blob);
            return blob;
        } catch (IOException e) {
            throw new StorageBoldException(e.getMessage());
        }
    }

}
