/*
 * Copyright 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.evolboot.core.data.jpa.convert;

import jakarta.persistence.Converter;
import org.evolboot.core.util.JsonUtil;
import org.springframework.util.StringUtils;

import jakarta.persistence.AttributeConverter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Converter(autoApply = true)
public class IntListConverter implements AttributeConverter<List<Integer>, String> {
    @Override
    public String convertToDatabaseColumn(List<Integer> attribute) {
        return Objects.isNull(attribute) ? null : JsonUtil.stringify(attribute);
    }

    @Override
    public List<Integer> convertToEntityAttribute(String dbData) {
        return StringUtils.isEmpty(dbData) ? new ArrayList<>() : JsonUtil.parse(dbData, List.class, Integer.class);
    }
}
