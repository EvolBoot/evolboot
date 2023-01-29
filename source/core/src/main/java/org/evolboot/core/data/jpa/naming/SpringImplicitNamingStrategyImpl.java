package org.evolboot.core.data.jpa.naming;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.spi.MetadataBuildingContext;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;

/**
 * @author evol
 * 
 */
public class SpringImplicitNamingStrategyImpl extends SpringImplicitNamingStrategy {

    @Override
    protected Identifier toIdentifier(String stringForm, MetadataBuildingContext buildingContext) {
        return super.toIdentifier(stringForm + "_", buildingContext);
    }
}
