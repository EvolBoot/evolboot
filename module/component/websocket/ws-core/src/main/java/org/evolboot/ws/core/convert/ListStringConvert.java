package org.evolboot.ws.core.convert;

import org.evolboot.core.util.JsonUtil;

import java.util.List;

/**
 * @author evol
 */
public class ListStringConvert implements WsAttributeConverter<String, List<String>> {

    @Override
    public List<String> convert(String s) {
        return JsonUtil.parse(s, List.class, String.class);
    }
}
