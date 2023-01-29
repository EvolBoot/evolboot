package org.evolboot.system.domain.appupgrade;

import org.evolboot.core.data.jpa.JpaAbstractEntity;
import org.evolboot.core.domain.AggregateRoot;
import org.evolboot.core.domain.IdGenerate;
import org.evolboot.core.domain.LocaleDomainPart;
import org.evolboot.system.domain.appupgrade.repository.jpa.convert.AppUpgradeLocaleListConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.List;


/**
 * APP更新
 *
 * @author evol
 */
@Table(name = "evol_system_app_upgrade")
@Getter
@Slf4j
@NoArgsConstructor
@Entity
public class AppUpgrade extends JpaAbstractEntity<Long> implements AggregateRoot<AppUpgrade>, LocaleDomainPart<AppUpgradeLocale> {

    @Id
    private Long id;

    private String appVersion;

    private String upgradeDate;

    private String downloadUrl;

    @Enumerated(EnumType.STRING)
    private ClientType clientType;

    private Boolean showDialog = true;

    @Convert(converter = AppUpgradeLocaleListConverter.class)
    private List<AppUpgradeLocale> locales;

    void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }


    void setUpgradeDate(String upgradeDate) {
        this.upgradeDate = upgradeDate;
    }

    void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    private void generateId() {
        this.id = IdGenerate.longId();
    }


    void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }

    void setShowDialog(Boolean showDialog) {
        this.showDialog = showDialog;
    }

    void setLocales(List<AppUpgradeLocale> locales) {
        this.locales = locales;
    }

    public AppUpgrade(String appVersion, String upgradeDate, String downloadUrl, ClientType clientType, Boolean showDialog, List<AppUpgradeLocale> locales) {
        generateId();
        setClientType(clientType);
        setAppVersion(appVersion);
        setUpgradeDate(upgradeDate);
        setDownloadUrl(downloadUrl);
        setShowDialog(showDialog);
        setLocales(locales);
    }


    @Override
    public Long id() {
        return id;
    }

    @Override
    public AppUpgrade root() {
        return this;
    }
}
