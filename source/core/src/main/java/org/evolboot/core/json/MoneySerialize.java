package org.evolboot.core.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.evolboot.core.DefaultCurrencyConfig;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * @author evol
 */
@JsonComponent
public class MoneySerialize extends JsonSerializer<BigDecimal> {


    private String parseMoney(BigDecimal money) {
        return DefaultCurrencyConfig.parseMoneyToString(money);
    }

    @Override
    public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(parseMoney(value));
    }


}
