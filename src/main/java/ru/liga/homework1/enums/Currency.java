package ru.liga.homework1.enums;

import java.util.Arrays;

/**
 * Валюта
 */
public enum Currency {
    EUR,
    USD,
    TRY,
    BGN,
    AMD;

    public static boolean checkCurrencyName(String currencyName) {
        return Arrays.stream(Currency.values()).anyMatch(cur -> cur.name().equals(currencyName));
    }
}
