package org.evolboot.system.domain.appupgrade;

import org.evolboot.core.data.Query;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * APP更新
 *
 * @author evol
 */
@Setter
@Getter
public class AppUpgradeQuery extends Query {

    @Builder
    public AppUpgradeQuery(Integer page, Integer limit) {
        super(page, limit);
    }
}
