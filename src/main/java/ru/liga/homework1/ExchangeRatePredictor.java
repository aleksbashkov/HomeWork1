package ru.liga.homework1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Осуществляет прогнозирование курса валюты и вывод результата
 */
public class ExchangeRatePredictor {

    final private PredictionAlgorithm algorithm;
    final private ExchangeRateFormatter formatter;
    final private DateTimeFormatter csvDateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public ExchangeRatePredictor(final PredictionAlgorithm algorithm,
                                 final ExchangeRateFormatter formatter) {
        this.algorithm = algorithm;
        this.formatter = formatter;
    }

    /**
     * Прогнозирование на завтра
     * @param currency валюта
     */
    public void printTomorrowExchangeRate(Currency currency) {
        printExchangeRate(currency, true);
    }

    /**
     * Прогнозирование на неделю
     * @param currency валюта
     */
    public void printNextWeekExchangeRate(Currency currency) {
        printExchangeRate(currency, false);
    }

    private void printExchangeRate(Currency currency, boolean tomorrowOnly) {
        var exchangeRateData = GetOrderedRateData(currency);
        if (exchangeRateData == null || exchangeRateData.size() == 0)
            throw new RuntimeException("No data for currency " + currency.name());
        var values = new ArrayList<>(exchangeRateData.stream().map(item -> item.getValue()).toList());
        var tomorrow = LocalDate.now().plusDays(1);
        var last = exchangeRateData.get(exchangeRateData.size()-1);
        boolean existsTomorrowValue = last.getKey().equals(tomorrow);
        var tomorrowExchangeRate = existsTomorrowValue
                ? last.getValue()
                : algorithm.doPrediction(values);
        System.out.println(
                formatter.formatCurrencyRate(tomorrow, tomorrowExchangeRate));

        if (tomorrowOnly)
            return;

        // прогноз ещё на 6 дней:
        if (!existsTomorrowValue) {
            values.add(tomorrowExchangeRate);
        }
        for (int i = 1; i < 7; i++) {
            var newValue = algorithm.doPrediction(values);
            System.out.println(
                    formatter.formatCurrencyRate(tomorrow.plusDays(i), newValue));
            values.add(newValue);
        }
    }

    /**
     * Считывает данные из csv-файла и сортирует их по дате.
     * Может кинуть RuntimeException.
      * @param currency валюта
     * @return Уопрядоченный по дате набор пар (дата, курс)
     */
    private List<Map.Entry<LocalDate, BigDecimal>> GetOrderedRateData(Currency currency) {
        List<String> lines;
        try {
            var inputStream = getClass().getResourceAsStream(String.format("/%s.csv", currency.name()));
            var bf = new BufferedReader(new InputStreamReader(inputStream));
            var header = bf.readLine();
            lines = bf.lines().toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (lines == null || lines.size() == 0)
            throw new RuntimeException("No data for currency " + currency.name());

        var res = new ArrayList<Map.Entry<LocalDate, BigDecimal>>();
        for (var line : lines)
            res.add(parseCsvString(line));
        return res.stream().sorted(Map.Entry.comparingByKey()).toList();
    }

    /**
     * Парсит строку csv-файла
     * @param csvLine строка csv-файла
     * @return Пара (дата, курс)
     */
    private Map.Entry<LocalDate, BigDecimal> parseCsvString(String csvLine) {
        var parts = csvLine.split(";");
        int cnt = Integer.parseInt(parts[0].replace(" ", ""));
        LocalDate date = LocalDate.parse(parts[1], csvDateFormatter);
        double rate = Double.parseDouble(parts[2].replace(',', '.'));
        return new AbstractMap.SimpleImmutableEntry<>(date, new BigDecimal(rate/cnt));
    }
}
