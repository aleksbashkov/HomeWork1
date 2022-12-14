package ru.liga.homework1.enums;

import java.util.Arrays;

/**
 * Период прогноза
 */
public enum Period {
    WEEK,
    MONTH;

    public static boolean checkPeriodName(String periodName) {
        return Arrays.stream(Period.values()).anyMatch(per -> per.name().equals(periodName));
    }
}
