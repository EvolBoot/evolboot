package projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.repository.jpa.convert;

import projectPackage.lrxxoiecygkjh.domain.xarvkgvvrllnc.entity.XarvkgvvrllncLocale;
import projectPackage.core.util.ExtendObjects;
import projectPackage.core.util.JsonUtil;
import com.google.common.collect.Lists;
import org.springframework.util.StringUtils;

import jakarta.persistence.AttributeConverter;
import java.util.List;

/**
 * @author authorxRQXP
 * @date datePlaceholder
 */
public class XarvkgvvrllncLocaleListConverter implements AttributeConverter<List<XarvkgvvrllncLocale>, String> {

    @Override
    public String convertToDatabaseColumn(List<XarvkgvvrllncLocale> attribute) {
        return ExtendObjects.isNull(attribute) ? null : JsonUtil.stringify(attribute);
    }

    @Override
    public List<XarvkgvvrllncLocale> convertToEntityAttribute(String dbData) {
        return StringUtils.isEmpty(dbData) ? Lists.newArrayList() : JsonUtil.parse(dbData, List.class, XarvkgvvrllncLocale.class);
    }
}
