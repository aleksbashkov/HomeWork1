package ru.liga.homework1.Enums;

import java.util.Arrays;

/**
 * Валюта
 */
public enum Currency {
    EUR,
    USD,
    TRY;

    public static boolean checkCurrencyName(String currencyName) {
        return Arrays.stream(Currency.values()).noneMatch(cur -> cur.name().equals(currencyName));
    }
}
