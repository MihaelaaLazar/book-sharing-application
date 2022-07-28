package com.endava.utils;

import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class EnumDecode {
    public static <T extends Enum<T>> T decode(final String code, Class<T> enumClass) {
        return Stream.of(
                        enumClass.getEnumConstants())
                .filter(targetEnum -> targetEnum.name().equals(code)).findFirst().orElse(null);
    }
}
