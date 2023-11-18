package org.evolboot.common.domain.config.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.common.domain.config.serivce.PropertyValue;
import org.evolboot.core.entity.AbstractEntity;
import org.evolboot.core.entity.AggregateRoot;
import org.evolboot.core.util.JsonUtil;

/**
 * @author evol
 */

@Entity
@Table(name = "evoltb_common_config")
@Slf4j
@NoArgsConstructor
public class Config extends AbstractEntity<String> implements AggregateRoot<Config> {

    @Id
    private String key;

    @Schema(description = "范围")
    private Scope scope;

    @Schema(description = "配置值")
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
