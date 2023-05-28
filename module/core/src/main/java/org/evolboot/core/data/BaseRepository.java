package org.evolboot.core.data;

/**
 * @author evol
 */
public interface BaseRepository<T, ID> {

    void deleteAllByIdInBatch(Iterable<ID> ids);


}
