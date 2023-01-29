package org.evolboot.configuration.domain;

import org.evolboot.core.domain.AbstractEntity;
import org.evolboot.core.domain.AggregateRoot;
import org.evolboot.core.util.JsonUtil;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

/**
 * @author evol
 */

@Entity
@Table(name = "evol_configuration")
@Slf4j
@NoArgsConstructor
public class Configuration extends AbstractEntity<String> implements AggregateRoot<org.evolboot.configuration.domain.Configuration> {

    @Id
    private String key;

    @Enumerated(EnumType.STRING)
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

    public <T extends org.evolboot.configuration.domain.PropertyValue> Configuration(String key, T propertyValue, Scope scope) {
        setKey(key);
        setScope(scope);
        setPropertyValue(JsonUtil.stringify(propertyValue));
    }

    String getPropertyValue() {
        return propertyValue;
    }

    public <T extends org.evolboot.configuration.domain.PropertyValue> T getPropertyObject(Class<? extends T> propertyValueClass) {
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
    public org.evolboot.configuration.domain.Configuration root() {
        return this;
    }
}
