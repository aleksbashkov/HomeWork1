package ru.liga.homework1.algorithms;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.liga.homework1.ExchangeRateDataProvider;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class LastYearAlgorithmTest {

    @DisplayName("Кидает исключение, если в исходных данных нет курсов за нужные число-месяц")
    @Test
    void shouldThrowExceptionIfNoData() {
        var data = ExchangeRateDataProvider.getSortedExchangeRateData("EUR.csv");
        var algorithm = new LastYearAlgorithm();
        assertThrows(RuntimeException.class, () -> algorithm.doPrediction(data, LocalDate.of(2023, Month.NOVEMBER, 7)));
    }

    @DisplayName("Проверка прогноза курса за 4 октября 2023")
    @Test
    void checkValidCommandParsing() {
        var data = ExchangeRateDataProvider.getSortedExchangeRateData("EUR.csv");
        var expectedValue = data.stream()
                .filter(item -> item.getDate().equals(LocalDate.of(2022, Month.OCTOBER, 4)))
                .findFirst()
                .orElseThrow()
                .getRate();
        var algorithm = new LastYearAlgorithm();
        assertEquals(expectedValue, algorithm.doPrediction(data, LocalDate.of(2023, Month.OCTOBER, 4)));
    }
}