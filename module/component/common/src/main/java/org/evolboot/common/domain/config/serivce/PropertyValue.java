package org.evolboot.common.domain.config.serivce;

import java.io.Serializable;

/**
 * @author evol
 */
public interface PropertyValue extends Serializable {

    void check();

    String key();


}
