package org.evolboot.storage.domain.blob.repository;

import org.evolboot.core.data.Page;
import org.evolboot.storage.domain.blob.Blob;
import org.evolboot.storage.domain.blob.BlobQuery;

/**
 * @author evol
 */
public interface BlobRepository {

    Blob save(Blob blob);

    Page<Blob> page(BlobQuery query);

}
