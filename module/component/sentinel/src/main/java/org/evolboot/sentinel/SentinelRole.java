package org.evolboot.sentinel;

import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.google.common.collect.Lists;
import lombok.*;
import org.evolboot.common.domain.config.serivce.PropertyValue;
import org.evolboot.core.util.ExtendObjects;

import java.util.List;

/**
 * @author evol
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class SentinelRole implements PropertyValue {


    public final static String KEY = "sentinel_role_";

    private List<FlowRule> list;


    @Override
    public void check() {

    }

    public List<FlowRule> getList() {
        if (ExtendObjects.isEmpty(list)) {
            return Lists.newArrayList();
        }
        return list;
    }

    @Override
    public String key() {
        return KEY;
    }
}
