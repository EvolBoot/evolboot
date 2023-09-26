package org.evolboot.shared.security;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author evol
 */
@Getter
@Setter
@NoArgsConstructor
public class EvolSession implements Serializable {

    private Long principalId;

    /**
     * 设备信息
     */
    private Map<String, EvolSessionDevice> devices = new HashMap<>();


    /**
     * 权限信息
     */
    private Set<String> authorities;

    public EvolSession(Long principalId) {
        this.principalId = principalId;
    }
}
