package org.evolboot.codegenerator;

import lombok.Getter;
import lombok.Setter;

/**
 * @author evol
 */
@Getter
@Setter
public class GenConfig {

    private String templatePath;
    private ConfigYaml placeholder;
    private ConfigYaml replace;

    @Getter
    @Setter
    public static class ConfigYaml {
        private String projectPackage;
        private String tablePrefix;
        private String author;
        private String boundContext;
        private String boundContextClass;
        private String boundContextUrl;
        private String domainName;
        private String classNamePrefix;
        private String instantiationObjectName;
        private String classNameUrl;
        private String remark;
        private String datePlaceholder;
        private String pkIdClass;

    }
}
