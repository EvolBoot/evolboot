package org.evolboot.content.domain.startuppage;

import org.evolboot.core.data.Query;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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
    public StartupPageQuery(Integer page, Integer limit, Boolean enable) {
        super(page, limit);
        this.enable = enable;
    }
}
