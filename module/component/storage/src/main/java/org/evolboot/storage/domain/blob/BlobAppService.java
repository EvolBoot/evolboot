package org.evolboot.storage.domain.blob;

import org.evolboot.core.data.Page;
import org.evolboot.shared.resource.ResourceOwner;
import org.evolboot.storage.domain.blob.entity.Blob;
import org.evolboot.storage.domain.blob.intercept.FileLimitType;
import org.evolboot.storage.domain.blob.dto.BlobQueryRequest;
import org.evolboot.storage.domain.blob.service.BlobCreateFactory;

import java.io.InputStream;

/**
 * @author evol
 */
public interface BlobAppService {

    Blob create(InputStream is, String filename, long fileSize, FileLimitType type, ResourceOwner resourceOwner, Long creatorUserId);
    Blob create(BlobCreateFactory.Request request);

    @Deprecated
    Blob createImage(InputStream is, String filename, long fileSize, Long ownerUserId);

    @Deprecated
    Blob createFile(InputStream is, String filename, long fileSize, Long ownerUserId);

    Page<Blob> page(BlobQueryRequest query);

    void delete(Long id);

}
