package org.evolboot.storage.domain.blob;

import org.evolboot.core.data.Page;
import org.evolboot.storage.domain.blob.entity.Blob;
import org.evolboot.storage.domain.blob.intercept.FileLimitType;
import org.evolboot.storage.domain.blob.dto.BlobQueryRequest;
import org.evolboot.storage.domain.blob.service.BlobCreateFactory;

import java.io.InputStream;

/**
 * @author evol
 */
public interface BlobAppService {

    Blob create(InputStream is, String filename, long fileSize, FileLimitType type, Long ownerUserId);
    Blob create(BlobCreateFactory.Request request);

    Blob createImage(InputStream is, String filename, long fileSize, Long ownerUserId);

    Blob createFile(InputStream is, String filename, long fileSize, Long ownerUserId);

    Page<Blob> page(BlobQueryRequest query);

}
