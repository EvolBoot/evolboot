package org.evolboot.storage.domain.blob;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.data.Page;
import org.evolboot.storage.domain.blob.entity.Blob;
import org.evolboot.storage.domain.blob.intercept.FileLimitType;
import org.evolboot.storage.domain.blob.repository.BlobRepository;
import org.evolboot.storage.domain.blob.service.BlobCreateFactory;
import org.evolboot.storage.domain.blob.dto.BlobQueryRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;

/**
 * @author evol
 */
@Service
@Slf4j
public class BlobAppServiceImpl implements BlobAppService {

    private final BlobCreateFactory factory;
    private final BlobRepository repository;

    public BlobAppServiceImpl(BlobCreateFactory factory, BlobRepository repository) {
        this.factory = factory;
        this.repository = repository;
    }

    @Transactional
    @Override
    public Blob create(InputStream is, String filename, long fileSize, FileLimitType type, Long ownerUserId) {
        return factory.create(new BlobCreateFactory.Request(
                is, filename, fileSize, type, ownerUserId
        ));
    }

    @Transactional
    @Override
    public Blob createImage(InputStream is, String filename, long fileSize, Long ownerUserId) {
        return factory.create(new BlobCreateFactory.Request(
                is, filename, fileSize, FileLimitType.IMAGE, ownerUserId
        ));
    }

    @Transactional
    @Override
    public Blob createFile(InputStream is, String filename, long fileSize, Long ownerUserId) {
        return factory.create(new BlobCreateFactory.Request(
                is, filename, fileSize, FileLimitType.FILE, ownerUserId
        ));
    }

    @Override
    public Blob create(BlobCreateFactory.Request request) {
        return factory.create(request);
    }
    @Override
    public Page<Blob> page(BlobQueryRequest query) {
        return repository.page(query);
    }


}
