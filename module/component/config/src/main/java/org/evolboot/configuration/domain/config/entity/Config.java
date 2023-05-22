package org.evolboot.configuration.domain.config.entity;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.configuration.domain.config.serivce.PropertyValue;
import org.evolboot.core.domain.AbstractEntity;
import org.evolboot.core.domain.AggregateRoot;
import org.evolboot.core.util.JsonUtil;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author evol
 */

@Entity
@Table(name = "evoltb_config")
@Slf4j
@NoArgsConstructor
public class Config extends AbstractEntity<String> implements AggregateRoot<Config> {

    @Id
    private String key;

    private Scope scope;

    private String propertyValue;


    void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }

    void setScope(Scope scope) {
        this.scope = scope;
    }

    void setKey(String key) {
        this.key = key;
    }

    public <T extends PropertyValue> Config(String key, T propertyValue, Scope scope) {
        setKey(key);
        setScope(scope);
        setPropertyValue(JsonUtil.stringify(propertyValue));
    }

    String getPropertyValue() {
        return propertyValue;
    }

    public <T extends PropertyValue> T getPropertyObject(Class<? extends T> propertyValueClass) {
        return JsonUtil.parse(getPropertyValue(), propertyValueClass);
    }

    public String getKey() {
        return key;
    }

    @Override
    public String id() {
        return key;
    }

    @Override
    public Config root() {
        return this;
    }
}
