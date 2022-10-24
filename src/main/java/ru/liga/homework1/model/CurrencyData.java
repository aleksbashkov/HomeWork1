package ru.liga.homework1.model;

import ru.liga.homework1.enums.Currency;

import java.util.List;

public class CurrencyData {
    private final Currency currency;
    private final List<RateForDate> data;

    public CurrencyData(Currency currency, List<RateForDate> data) {
        this.currency = currency;
        this.data = data;
    }

    public Currency getCurrency() {
        return currency;
    }

    public List<RateForDate> getData() {
        return data;
    }
}
