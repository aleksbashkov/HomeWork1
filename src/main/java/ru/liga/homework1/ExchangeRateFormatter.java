package ru.liga.homework1;

/**
 * Интерфейс для представления спрогнозированных курсов
 */
@FunctionalInterface
interface ExchangeRateFormatter {
    /**
     * Метод форматирует данные - дата и соответствующий курс валюты
      * @param rate курс на дату
     * @return строка с отображением даты и курса
     */
    String formatCurrencyRate(RateForDate rate);
}

