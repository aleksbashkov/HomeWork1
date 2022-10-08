package ru.liga.homework1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Stream;

/**
 * Класс для чтения курсов из csv
 */
class ExchangeRateDataReader {

    final private DateTimeFormatter csvDateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    /**
     * Читает курсы из указанного файла csv
     * Может кинуть RuntimeException.
      * @param csvFileName - имя csv файла
     * @return Список курсов в зависимости от даты
     */
    public List<Map.Entry<LocalDate, BigDecimal>> readExchangeData(String csvFileName) {
        Stream<String> lines;
        try {
            var inputStream = getClass().getResourceAsStream(csvFileName);
            if (inputStream == null)
                throw new RuntimeException("Не найден файл " + csvFileName);
            var bf = new BufferedReader(new InputStreamReader(inputStream));
            bf.readLine(); // read csv header
            lines = bf.lines();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        var res = new ArrayList<Map.Entry<LocalDate, BigDecimal>>();
        lines.forEach(line -> res.add(parseCsvString(line)));
        return res;
    }

    /**
     * Парсит строку csv-файла.
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
