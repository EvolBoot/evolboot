package org.evolboot.common.domain.config;

import cn.hutool.core.util.ReflectUtil;
import org.evolboot.common.domain.config.entity.Config;
import org.evolboot.common.domain.config.entity.Scope;
import org.evolboot.common.domain.config.repository.ConfigRepository;
import org.evolboot.common.domain.config.serivce.ConfigCreateFactory;
import org.evolboot.common.domain.config.serivce.ConfigSupportService;
import org.evolboot.common.domain.config.serivce.PropertyValue;
import org.evolboot.core.util.ExtendObjects;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author evol
 */
@Service
public class ConfigAppServiceImpl implements ConfigAppService {

    private final ConfigRepository repository;

    private final ConfigSupportService supportService;

    private final ConfigCreateFactory factory;

    protected ConfigAppServiceImpl(ConfigRepository repository, ConfigSupportService supportService, ConfigCreateFactory factory) {
        this.repository = repository;
        this.supportService = supportService;
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
