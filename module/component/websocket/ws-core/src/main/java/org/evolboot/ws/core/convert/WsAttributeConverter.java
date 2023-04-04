package org.evolboot.ws.core.convert;

/**
 * @author evol
 */
@FunctionalInterface
public interface WsAttributeConverter<T, R> {

    R convert(T t);

}
