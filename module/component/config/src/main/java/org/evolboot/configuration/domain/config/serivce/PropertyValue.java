package org.evolboot.configuration.domain.config.serivce;

import java.io.Serializable;

/**
 * @author evol
 */
public interface PropertyValue extends Serializable {

    void check();

    String key();


}
