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

    private String principalName;

    /**
     * 设备信息
     */
    private Map<String, EvolSessionDevice> devices = new HashMap<>();


    /**
     * 权限信息
     */
    private Set<String> authorities;

    public EvolSession(Long principalId, String principalName) {
        this.principalId = principalId;
        this.principalName = principalName;
    }
}
