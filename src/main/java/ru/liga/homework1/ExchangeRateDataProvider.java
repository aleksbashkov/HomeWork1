package ru.liga.homework1;

import ru.liga.homework1.Enums.Currency;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class ExchangeRateDataProvider {
    /**
     * Предоставляет курсы для конкретной валюты
     * @param currency - валюта
     * @return упорядоченный по дате список пар (дата, курс валюты)
     */
    public static List<Map.Entry<LocalDate, BigDecimal>> getSortedExchangeRateData(Currency currency) {
        var reader = new ExchangeRateDataReader();
        var exchangeRateData = reader.readExchangeData(String.format("/%s.csv", currency.name()));
        if (exchangeRateData.size() == 0)
            throw new RuntimeException("Нет данных для валюты " + currency.name());

        // упорядочиваем данные:
        exchangeRateData.sort(Map.Entry.comparingByKey());

        return exchangeRateData;
    }
}
