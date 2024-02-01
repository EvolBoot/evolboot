package org.evolboot.core.data;

import java.util.List;

/**
 * @author evol
 */
public interface BaseRepository<T, ID> {

    void deleteAllByIdInBatch(Iterable<ID> ids);

    <S extends T> List<S> saveAll(Iterable<S> entities);


}
