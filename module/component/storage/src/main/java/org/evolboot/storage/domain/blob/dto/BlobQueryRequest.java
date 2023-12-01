package org.evolboot.storage.domain.blob.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.evolboot.core.data.Direction;
import org.evolboot.core.data.Query;

/**
 * @author evol
 */
@Setter
@Getter
public class BlobQueryRequest extends Query {

    @Builder
    public BlobQueryRequest(Integer page, Integer limit, String orderField, Direction order) {
        super(page, limit, orderField, order);
    }
}
