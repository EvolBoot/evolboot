package org.evolboot.security.api;

import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author evol
 * 
 */
@Getter
@Setter
@NoArgsConstructor
public class EvolSession implements Serializable {

    private Long principalId;

    /**
     * 设备信息
     */
    private Map<String, EvolSessionDevice> devices = Maps.newHashMap();


    /**
     * 权限信息
     */
    private List<String> authorities;

    public EvolSession(Long principalId) {
        this.principalId = principalId;
    }
}
