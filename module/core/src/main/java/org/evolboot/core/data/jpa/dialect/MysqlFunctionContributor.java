package org.evolboot.core.data.jpa.dialect;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.boot.model.FunctionContributions;
import org.hibernate.boot.model.FunctionContributor;
import org.hibernate.type.StandardBasicTypes;

/**
 * @author evol
 */
@Slf4j
public class MysqlFunctionContributor implements FunctionContributor {
    @Override
    public void contributeFunctions(FunctionContributions functionContributions) {
        functionContributions.getFunctionRegistry().registerPattern("bitand", "(?1 & ?2)", functionContributions.getTypeConfiguration().getBasicTypeRegistry().resolve(StandardBasicTypes.INTEGER));
        functionContributions.getFunctionRegistry().registerPattern("bitor", "(?1 | ?2)", functionContributions.getTypeConfiguration().getBasicTypeRegistry().resolve(StandardBasicTypes.INTEGER));
        functionContributions.getFunctionRegistry().registerPattern("bitxor", "(?1 ^ ?2)", functionContributions.getTypeConfiguration().getBasicTypeRegistry().resolve(StandardBasicTypes.INTEGER));
        log.info("加载 MysqlFunctionContributor");
    }
}
