package org.evolboot.shared.lang;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.swing.plaf.PanelUI;

/**
 * @author evol
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CurrentPrincipal {

    private Long userId;

    private Long tenantId;

    public static CurrentPrincipal onlyTenant(Long tenantId) {
        return new CurrentPrincipal(null, tenantId);
    }

    public static CurrentPrincipal onlyUser(Long userId) {
        return new CurrentPrincipal(userId, null);
    }
}
