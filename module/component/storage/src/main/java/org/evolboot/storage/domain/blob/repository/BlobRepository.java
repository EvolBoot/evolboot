package org.evolboot.storage.domain.blob.repository;

import org.evolboot.core.data.BaseRepository;
import org.evolboot.core.data.Page;
import org.evolboot.storage.domain.blob.entity.Blob;
import org.evolboot.storage.domain.blob.service.BlobQuery;

/**
 * @author evol
 */
public interface BlobRepository extends BaseRepository<Blob, Long> {

    Blob save(Blob blob);

    Page<Blob> page(BlobQuery query);

}
