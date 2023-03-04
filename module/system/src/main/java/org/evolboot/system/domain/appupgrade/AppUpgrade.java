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
@Table(name = "evoltb_system_app_upgrade")
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

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }


    public void setUpgradeDate(String upgradeDate) {
        this.upgradeDate = upgradeDate;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }



    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }

    public void setShowDialog(Boolean showDialog) {
        this.showDialog = showDialog;
    }

    public void setLocales(List<AppUpgradeLocale> locales) {
        this.locales = locales;
    }

    private void generateId() {
        this.id = IdGenerate.longId();
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
