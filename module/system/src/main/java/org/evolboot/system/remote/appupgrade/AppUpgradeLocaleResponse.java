package org.evolboot.system.remote.appupgrade;

import org.evolboot.core.data.Page;
import org.evolboot.core.data.PageImpl;
import org.evolboot.system.domain.appupgrade.AppUpgrade;
import org.evolboot.system.domain.appupgrade.AppUpgradeLocale;
import org.evolboot.system.domain.appupgrade.ClientType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author evol
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppUpgradeLocaleResponse {


    private Long id;

    private String appVersion;

    private String upgradeDate;

    private String downloadUrl;

    private ClientType clientType;

    private Boolean showDialog;

    private AppUpgradeLocale locale;

    public static AppUpgradeLocaleResponse of(AppUpgrade row) {
        if (row == null) {
            return null;
        }
        AppUpgradeLocale locale = row.findLocaleByCurrentLanguage(AppUpgradeLocale.class);
        return new AppUpgradeLocaleResponse(
                row.id(), row.getAppVersion(), row.getUpgradeDate(), row.getDownloadUrl(), row.getClientType(), row.getShowDialog(), locale
        );
    }


    /**
     * 分页
     *
     * @param page
     * @return
     */
    public static Page<AppUpgradeLocaleResponse> of(Page<AppUpgrade> page) {
        return PageImpl.<AppUpgradeLocaleResponse>builder()
                .page(page.getPage())
                .limit(page.getLimit())
                .totalSize(page.getTotalSize())
                .list(of(page.getList()))
                .build();
    }

    /**
     * 列表
     *
     * @param list
     * @return
     */
    public static List<AppUpgradeLocaleResponse> of(List<AppUpgrade> list) {
        return list.stream().map(AppUpgradeLocaleResponse::of).collect(Collectors.toList());
    }

}
