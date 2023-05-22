package org.evolboot.configuration.domain.config;

import org.evolboot.configuration.domain.config.serivce.PropertyValue;

/**
 * @author evol
 */
public interface ConfigAppService {

    PropertyValue setPropertyValue(PropertyValue propertyValue);

    <T extends PropertyValue> T getByKey(String key, Class<? extends T> propertyValueClass);

}
