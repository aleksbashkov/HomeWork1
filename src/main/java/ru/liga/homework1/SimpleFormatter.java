package ru.liga.homework1;

import ru.liga.homework1.model.RateForDate;

public class SimpleFormatter implements ExchangeRateFormatter {
    @Override
    public String formatCurrencyRate(RateForDate rate) {
        return String.format("%1$ta %1$td.%1$tm.%1$tY - %2$.2f", rate.getDate(), rate.getRate());
    }
}
