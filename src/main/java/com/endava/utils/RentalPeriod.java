package com.endava.utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.time.Period;

public enum RentalPeriod {

    ONE_WEEK("ONE_WEEK", Period.ofDays(7)),
    TWO_WEEKS("TWO_WEEKS", Period.ofDays(14)),
    THREE_WEEKS("THREE_WEEKS", Period.ofDays(21)),
    ONE_MONTH("ONE_MONTH", Period.ofDays(30)),
    TWO_MONTHS("TWO_MONTHS", Period.ofDays(60)),
    THREE_MONTHS("THREE_MONTHS", Period.ofDays(90));

    final String key;
    private final Period period;

    private RentalPeriod(String key, Period period) {
        this.key = key;
        this.period = period;
    }

    @JsonCreator
    public final static RentalPeriod decode(final String code) {
        return EnumDecode.decode(code, RentalPeriod.class);
    }

    @JsonValue
    public final Period getPeriod() {
        return period;
    }
}