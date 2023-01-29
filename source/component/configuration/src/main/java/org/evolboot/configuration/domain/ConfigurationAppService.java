package org.evolboot.configuration.domain;

/**
 * @author evol
 */
public interface ConfigurationAppService {

    PropertyValue setPropertyValue(PropertyValue propertyValue);

    <T extends PropertyValue> T getByKey(String key, Class<? extends T> propertyValueClass);

}
