package ru.liga.homework1;

import ru.liga.homework1.enums.Currency;
import ru.liga.homework1.model.RateForDate;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;

public class ExchangeRateDataProvider {

    /**
     * Предоставляет курсы из файла
     * @param fileName - имя файла с курсами валюты
     * @return упорядоченный по дате список курсов валюты с датами
     */
    public static List<RateForDate> getSortedExchangeRateData(String fileName) {
        // считываем данные из файла:
        var reader = new ExchangeRateDataReader();
        var exchangeRateData = reader.readExchangeData(fileName);
        if (exchangeRateData.size() == 0)
            throw new RuntimeException("Нет данных в файле " + fileName);

        // сортируем по дате:
        exchangeRateData.sort(Comparator.comparing(RateForDate::getDate));

        // дополняем значениями для отсутствующих дней:
        addMissingExchangeRate(exchangeRateData);

        return exchangeRateData;
    }

    /**
     * Предоставляет курсы для конкретной валюты
     * @param currency - валюта
     * @return упорядоченный по дате список курсов валюты с датами
     */
    public static List<RateForDate> getSortedExchangeRateData(Currency currency) {
        return getSortedExchangeRateData(String.format("%s.csv", currency.name()));
    }

    private static void addMissingExchangeRate(List<RateForDate> exchangeRateData) {
        var firstDate = exchangeRateData.get(0).getDate();
        var lastDate = exchangeRateData.get(exchangeRateData.size()-1).getDate();
        var dayCnt = ChronoUnit.DAYS.between(firstDate, lastDate);
        for (int i = 1; i < dayCnt; i++) {
            LocalDate curDate = firstDate.plusDays(i);
            if (!exchangeRateData.get(i).getDate().equals(curDate)) // отсутствует курс за дату
                exchangeRateData.add(i, new RateForDate(curDate, exchangeRateData.get(i-1).getRate())); // берём предыдущее значение курса
        }
    }
}
