package org.evolboot.configuration.domain;

/**
 * @author evol
 */
public interface ConfigAppService {

    PropertyValue setPropertyValue(PropertyValue propertyValue);

    <T extends PropertyValue> T getByKey(String key, Class<? extends T> propertyValueClass);

}
