package org.evolboot.core.data;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.evolboot.core.util.ExtendObjects;
import lombok.Builder;

import java.util.Collections;
import java.util.List;

/**
 * @author evol
 */
public class PageImpl<T> implements Page<T> {


    private int page = 0;
    private int limit = 0;
    private int size = 0;
    private int totalPage = 0;
    private long totalSize = 0;
    private List<T> list;

    public PageImpl(List<T> list) {
        setList(list);
        setPage(Pageable.DEFAULT_PAGE);
        setLimit(this.list.size());
        setTotalPage(1);
        setTotalSize(this.list.size());
    }

    @Builder
    public PageImpl(int page, int limit, long totalSize, List<T> list) {
        setList(list);
        setPage(page);
        setLimit(limit);
        setTotalSize(totalSize);
        computeTotalPages();
    }

    private void computeTotalPages() {
        if (limit <= 0) {
            return;
        }
        this.totalPage = (int) ((totalSize + limit - 1) / limit);
    }


    @Override
    public int getPage() {
        return page;
    }

    @Override
    public int getLimit() {
        return limit;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public List<T> getList() {
        return list;
    }

    @Override
    public int getTotalPages() {
        return totalPage;
    }

    @Override
    public long getTotalSize() {
        return totalSize;
    }

    @Override
    public boolean getLastPage() {
        return this.page >= totalPage;
    }

    private void setPage(int page) {
        this.page = Math.max(page, Pageable.DEFAULT_PAGE);
    }

    @Override
    @JsonIgnore
    public boolean isEmpty() {
        return false;
    }

    private void setLimit(int limit) {
        this.limit = limit;
    }

    private void setSize(int size) {
        this.size = size;
    }

    private void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    private void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }

    private void setList(List<T> list) {
        this.list = ExtendObjects.isEmpty(list) ? Collections.emptyList() : list;
        this.size = this.list.size();
    }

    public static <R> Page<R> of(org.springframework.data.domain.Page<R> page) {
        return PageImpl.<R>builder()
                .list(page.getContent())
                .page(page.getNumber() + 1)
                .limit(page.getSize())
                .totalSize(page.getTotalElements())
                .build();
    }


    public static <R> Page<R> of(IPage<R> page) {
        return PageImpl.<R>builder()
                .list(page.getRecords())
                .page((int) page.getCurrent())
                .limit((int) page.getSize())
                .totalSize(page.getTotal())
                .build();
    }


}
