package org.evolboot.locale.domain.language;

import org.evolboot.core.data.Query;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author evol
 */
@Setter
@Getter
public class LanguageQuery extends Query {
    private String prefix;
    private String language;
    private String scriptCode;
    private String region;
    private String languageLocalRemark;
    private String languageRemark;
    private String flag;

    @Builder
    public LanguageQuery(Integer page, Integer limit, String prefix, String language, String scriptCode, String region, String languageLocalRemark, String languageRemark, String flag) {
        super(page, limit);
        this.prefix = prefix;
        this.language = language;
        this.scriptCode = scriptCode;
        this.region = region;
        this.languageLocalRemark = languageLocalRemark;
        this.languageRemark = languageRemark;
        this.flag = flag;
    }
}
