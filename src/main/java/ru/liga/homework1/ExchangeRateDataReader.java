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
     * @return Список курсов с датами
     */
    public List<RateForDate> readExchangeData(String csvFileName) {
        Stream<String> lines;
        try (var inputStream = getClass().getResourceAsStream(csvFileName)) {
            if (inputStream == null)
                throw new RuntimeException("Не найден файл " + csvFileName);
            try (var bf = new BufferedReader(new InputStreamReader(inputStream))) {
                bf.readLine(); // read csv header
                lines = bf.lines();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        var res = new ArrayList<RateForDate>();
        lines.forEach(line -> res.add(parseCsvString(line)));
        return res;
    }

    /**
     * Парсит строку csv-файла.
     */
    private RateForDate parseCsvString(String csvLine) {
        var parts = csvLine.split(";");
        int cnt = Integer.parseInt(parts[0].replace(" ", ""));
        LocalDate date = LocalDate.parse(parts[1], csvDateFormatter);
        double rate = Double.parseDouble(parts[2].replace(',', '.'));
        return new RateForDate(date, new BigDecimal(rate/cnt));
    }
}
