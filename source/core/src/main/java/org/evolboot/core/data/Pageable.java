
package org.evolboot.core.data;

import lombok.Builder;

import java.util.Objects;

public class Pageable {

    public static final int DEFAULT_PAGE = 1;

    public static final int DEFAULT_LIMIT = 20;

    public static final int MIN_LIMIT = 1;

    public static final int MAX_LIMIT = 100;

    private Integer page = DEFAULT_PAGE;

    private Integer limit = DEFAULT_LIMIT;

    @Builder
    public Pageable(Integer page, Integer limit) {
        setPage(page);
        setLimit(limit);
    }

    public Integer getPage() {
        return this.page;
    }

    /**
     * offset 从 0 开始计算，比如 limit 10 ,
     * 说明一页要10条，page 从 1 开始计算，
     * 如果 1 * 10 = 10,变成 从10 开始查询，
     * 所以需要减去 1
     *
     * @return
     */
    public long getOffset() {
        return (page - 1) * limit;
    }


    public int getJpaPage() {
        return this.page - 1;
    }

    public void setPage(Integer page) {
        if (Objects.nonNull(page)) {
            this.page = Math.max(DEFAULT_PAGE, page);
        } else {
            this.page = DEFAULT_PAGE;
        }
    }

    public static Pageable of(int page, int limit) {
        return Pageable.builder()
                .page(page)
                .limit(limit)
                .build();
    }

    public Integer getLimit() {
        return this.limit;
    }

    public void setLimit(Integer limit) {
        if (Objects.nonNull(limit)) {
            this.limit = Math.min(Math.max(MIN_LIMIT, limit), MAX_LIMIT);
        } else {
            this.limit = MIN_LIMIT;
        }
    }
}
