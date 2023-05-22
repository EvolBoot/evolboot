package org.evolboot.storage.domain.blob;

import org.evolboot.core.data.Page;
import org.evolboot.storage.domain.blob.entity.Blob;
import org.evolboot.storage.domain.blob.intercept.FileLimitType;
import org.evolboot.storage.domain.blob.service.BlobQuery;

import java.io.InputStream;

/**
 * @author evol
 */
public interface BlobAppService {

    String create(InputStream is, String filename, long fileSize, FileLimitType type, Long ownerUserId);

    String createImage(InputStream is, String filename, long fileSize, Long ownerUserId);

    String createFile(InputStream is, String filename, long fileSize, Long ownerUserId);

    Page<Blob> page(BlobQuery query);

}
