package ru.liga.homework1;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SimpleFormatter implements ExchangeRateFormatter {
    @Override
    public String formatCurrencyRate(LocalDate date, BigDecimal exchangeRate) {
        return String.format("%1$ta %1$td.%1$tm.%1$tY - %2$.2f", date, exchangeRate);
    }
}
