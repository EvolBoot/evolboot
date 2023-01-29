package org.evolboot.storage.domain.blob;

import org.evolboot.core.data.Query;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author evol
 */
@Setter
@Getter
public class BlobQuery extends Query {

    @Builder
    public BlobQuery(Integer page, Integer limit) {
        super(page, limit);
    }
}
