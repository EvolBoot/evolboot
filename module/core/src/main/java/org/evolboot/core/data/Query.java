package org.evolboot.core.data;

import org.springframework.data.domain.PageRequest;


public class Query {

    private Pageable pageable;

    private String sortField;

    private Direction direction;

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }

    public Query(Integer page, Integer limit, String sortField, Direction direction) {
        this.sortField = sortField;
        this.direction = direction;
        setPageable(Pageable.builder()
                .page(page)
                .limit(limit)
                .build());
    }

    public PageRequest toJpaPageRequest() {
        return PageRequest.of(
                getPageable().getJpaPage(),
                getPageable().getLimit()
        );
    }

    public <T> com.baomidou.mybatisplus.extension.plugins.pagination.Page<T> toMybatisPage() {
        return new com.baomidou.mybatisplus.extension.plugins.pagination.Page<T>(pageable.getPage(), pageable.getLimit());
    }


    public Pageable getPageable() {
        return pageable;
    }

    public String getSortField() {
        return sortField;
    }

    public Direction getDirection() {
        return direction;
    }
}
