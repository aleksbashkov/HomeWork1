package ru.liga.homework1;

import ru.liga.homework1.enums.Currency;

import java.util.Comparator;
import java.util.List;

public class ExchangeRateDataProvider {
    /**
     * Предоставляет курсы для конкретной валюты
     * @param currency - валюта
     * @return упорядоченный по дате список курсов валюты с датами
     */
    public static List<RateForDate> getSortedExchangeRateData(Currency currency) {
        var reader = new ExchangeRateDataReader();
        var exchangeRateData = reader.readExchangeData(String.format("/%s.csv", currency.name()));
        if (exchangeRateData.size() == 0)
            throw new RuntimeException("Нет данных для валюты " + currency.name());

        return exchangeRateData.stream()
                .sorted(Comparator.comparing(RateForDate::getDate))
                .toList();
    }
}
