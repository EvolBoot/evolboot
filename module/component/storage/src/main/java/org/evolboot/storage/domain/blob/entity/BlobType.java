package org.evolboot.storage.domain.blob.entity;

import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum BlobType {
    DIRECTORY(0), FILE(1);

    private final Integer value;

    private static final Map<Integer, BlobType> VALUES = Maps.newHashMapWithExpectedSize(BlobType.values().length);

    static {
        Arrays.stream(BlobType.values()).forEach(e -> {
            VALUES.put(e.getValue(), e);
        });
    }


    public static BlobType convertTo(Integer value) {
        return VALUES.get(value);
    }


}
