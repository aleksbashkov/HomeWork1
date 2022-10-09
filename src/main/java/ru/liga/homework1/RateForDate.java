package ru.liga.homework1;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Класс содержит курс валюты за определённую дату
 */
public class RateForDate {

    private final LocalDate date;

    private final BigDecimal rate;

    public RateForDate(LocalDate date, BigDecimal rate) {
        this.date = date;
        this.rate = rate;
    }

    public LocalDate getDate() {
        return date;
    }

    public BigDecimal getRate() {
        return rate;
    }

    @Override
    public String toString() {
        return String.format("%1$td.%1$tm.%1$tY - %2$.2f", date, rate);
    }
}
