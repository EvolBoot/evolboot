package org.evolboot.ws.core.convert;

import org.evolboot.core.util.JsonUtil;

/**
 * @author evol
 */
public class ListStringArrayConvert implements WsAttributeConverter<String, String[]> {

    @Override
    public String[] convert(String s) {
        return JsonUtil.parse(s, String[].class);
    }
}
