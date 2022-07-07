package com.endava.utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.time.Period;
import java.util.stream.Stream;

public enum DefaultPeriodsForExtendedTime {

    ONE_WEEK("ONE_WEEK", Period.ofDays(7)),
    TWO_WEEKS("TWO_WEEKS", Period.ofDays(14));


    final String key;
    private final Period period;

    private DefaultPeriodsForExtendedTime(String key, Period period) {
        this.key = key;
        this.period = period;
    }

    @JsonCreator
    public final static DefaultPeriodsForExtendedTime decode(final String code) {
        return Stream.of(DefaultPeriodsForExtendedTime.values()).filter(targetEnum -> targetEnum.key.equals(code)).findFirst().orElse(null);
    }

    @JsonValue
    public final Period getPeriod() {
        return period;
    }
}