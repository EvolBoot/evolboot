package org.evolboot.core.data;

import java.util.Iterator;
import java.util.List;

/**
 * @author evol
 */
public interface Page<T> extends Slice<T> {

    /**
     * 当前页数
     *
     * @return
     */
    int getPage();

    /**
     * 当前页要求数量
     *
     * @return
     */
    int getLimit();

    /**
     * 当前页真实数量
     *
     * @return
     */
    int getSize();

    /**
     * 数据
     *
     * @return
     */
    List<T> getList();


    /**
     * 总页数
     *
     * @return
     */
    int getTotalPages();

    /**
     * 总数量
     *
     * @return
     */
    long getTotalSize();

    boolean getLastPage();

    @Override
    default Iterator<T> iterator() {
        return this.getList().iterator();
    }

}
