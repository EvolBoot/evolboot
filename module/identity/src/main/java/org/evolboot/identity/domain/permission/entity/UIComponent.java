package org.evolboot.identity.domain.permission.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

/**
 * @author evol
 */
@Embeddable
@Getter
@NoArgsConstructor
public class UIComponent {

    private String uiName;
    private String uiRoute;
    private String uiIcon;

    private void setUiName(String uiName) {
        this.uiName = uiName;
    }

    private void setUiRoute(String uiRoute) {
        this.uiRoute = uiRoute;
    }

    private void setUiIcon(String uiIcon) {
        this.uiIcon = uiIcon;
    }

    @Builder
    public UIComponent(String uiName, String uiRoute, String uiIcon) {
        setUiIcon(uiIcon);
        setUiRoute(uiRoute);
        setUiName(uiName);
    }
}
