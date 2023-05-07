package org.evolboot.configuration.domain.config;

/**
 * @author evol
 */
public interface ConfigAppService {

    PropertyValue setPropertyValue(PropertyValue propertyValue);

    <T extends PropertyValue> T getByKey(String key, Class<? extends T> propertyValueClass);

}
