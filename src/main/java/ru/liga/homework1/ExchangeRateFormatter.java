package ru.liga.homework1;

import ru.liga.homework1.model.RateForDate;

/**
 * Интерфейс для представления спрогнозированных курсов
 */
@FunctionalInterface
interface ExchangeRateFormatter {//?? delete it?
    /**
     * Метод форматирует данные - дата и соответствующий курс валюты
      * @param rate курс на дату
     * @return строка с отображением даты и курса
     */
    String formatCurrencyRate(RateForDate rate);
}

