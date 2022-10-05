package ru.liga.homework1.Enums;

import java.util.Arrays;

/**
 * Период прогноза
 */
public enum Period {
    TOMORROW,
    WEEK;

    public static boolean checkPeriodName(String periodName) {
        return Arrays.stream(Period.values()).anyMatch(per -> per.name().equals(periodName));
    }
}
