package ru.liga.homework1;

import java.math.BigDecimal;
import java.time.LocalDate;


/**
 * Интерфейс для представления спрогнозированных курсов
 */
@FunctionalInterface
interface ExchangeRateFormatter {
    /**
     * Метод форматирует данные - дата и соответствующий курс валюты
      * @param date дата
     * @param exchangeRate курс валюты
     * @return строка с отображением даты и курса
     */
    String formatCurrencyRate(LocalDate date, BigDecimal exchangeRate);
}

