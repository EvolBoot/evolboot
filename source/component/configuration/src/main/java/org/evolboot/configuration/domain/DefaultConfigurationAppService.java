package org.evolboot.configuration.domain;

import cn.hutool.core.util.ReflectUtil;
import org.evolboot.configuration.domain.repository.ConfigurationRepository;
import org.evolboot.core.util.ExtendObjects;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author evol
 */
@Service
public class DefaultConfigurationAppService extends ConfigurationSupportService implements ConfigurationAppService {

    private final ConfigurationCreateFactory factory;

    protected DefaultConfigurationAppService(ConfigurationRepository repository, ConfigurationCreateFactory factory) {
        super(repository);
        this.factory = factory;

    }

    @Override
    @Transactional
    public PropertyValue setPropertyValue(PropertyValue propertyValue) {
        return factory.execute(propertyValue.key(), propertyValue, Scope.APPLICATION);
    }

    @Override
    public <T extends PropertyValue> T getByKey(String key, Class<? extends T> propertyValueClass) {
        Configuration configuration = repository.findByKey(key).orElse(null);
        if (ExtendObjects.isNull(configuration)) {
            return ReflectUtil.newInstance(propertyValueClass.getName());
        }
        return configuration.getPropertyObject(propertyValueClass);
    }

}
