package org.evolboot.system.domain.startuppage.service;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.evolboot.core.data.Direction;
import org.evolboot.core.data.Query;

/**
 * 启动页
 *
 * @author evol
 */
@Setter
@Getter
public class StartupPageQuery extends Query {

    private final Boolean enable;

    @Builder
    public StartupPageQuery(Integer page, Integer limit, Boolean enable, String orderField, Direction order) {
        super(page, limit, orderField, order);
        this.enable = enable;
    }
}
