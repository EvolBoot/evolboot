package org.evolboot.storage.domain.blob;

import org.evolboot.core.data.Page;
import org.evolboot.storage.domain.blob.intercept.FileLimitType;
import org.evolboot.storage.domain.blob.repository.BlobRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;

/**
 * @author evol
 * 
 */
@Service
@Slf4j
public class DefaultBlobAppService implements BlobAppService {

    private final BlobCreateFactory factory;
    private final BlobRepository repository;

    public DefaultBlobAppService(BlobCreateFactory factory, BlobRepository repository) {
        this.factory = factory;
        this.repository = repository;
    }

    @Transactional
    @Override
    public String create(InputStream is, String filename, long fileSize, FileLimitType type, Long ownerUserId) {
        return factory.create(is, filename, fileSize, type, ownerUserId).getUrl();
    }

    @Transactional
    @Override
    public String createImage(InputStream is, String filename, long fileSize, Long ownerUserId) {
        return factory.create(is, filename, fileSize, FileLimitType.IMAGE, ownerUserId).getUrl();
    }

    @Transactional
    @Override
    public String createFile(InputStream is, String filename, long fileSize, Long ownerUserId) {
        return factory.create(is, filename, fileSize, FileLimitType.FILE, ownerUserId).getUrl();
    }

    @Override
    public Page<Blob> page(BlobQuery query) {
        return repository.page(query);
    }


}
