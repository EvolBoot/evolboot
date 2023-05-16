package org.evolboot.system.domain.appupgrade;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.evolboot.core.data.Query;

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
