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
public class DefaultConfigAppService extends ConfigSupportService implements ConfigAppService {

    private final ConfigCreateFactory factory;

    protected DefaultConfigAppService(ConfigurationRepository repository, ConfigCreateFactory factory) {
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
        Config config = repository.findByKey(key).orElse(null);
        if (ExtendObjects.isNull(config)) {
            return ReflectUtil.newInstance(propertyValueClass.getName());
        }
        return config.getPropertyObject(propertyValueClass);
    }

}
