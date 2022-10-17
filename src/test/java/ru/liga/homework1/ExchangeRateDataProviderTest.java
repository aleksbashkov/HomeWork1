package ru.liga.homework1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

import ru.liga.homework1.enums.Currency;
import ru.liga.homework1.model.RateForDate;

class ExchangeRateDataProviderTest {

    @DisplayName("Кидает исключение, когда указываем валюту, для которой не существует файла с курсами")
    @Test
    void shouldThrowRuntimeExceptionIfNotExistsFileForCurrency() {
        assertThrows(RuntimeException.class, () -> ExchangeRateDataProvider.getSortedExchangeRateData(Currency.AMD));
    }

    @DisplayName("Проверяем, что считанные курсы дополняются данными за отсутствующие дни")
    @Test
    void checkAdditionDataForMissingDays() {
        var reader = new ExchangeRateDataReader();
        var readData = reader.readExchangeData("EUR.csv");
        var finalData = ExchangeRateDataProvider.getSortedExchangeRateData(Currency.EUR);
        assertTrue(finalData.size() > readData.size());
    }

    @DisplayName("Проверяем, что считанные из файла курсы отсортированы по дате")
    @Test
    void checkFinalDataIsSortedByDate() {
        var data = ExchangeRateDataProvider.getSortedExchangeRateData(Currency.EUR);
        var rates = data.stream()
                .map(RateForDate::getRate)
                .toArray();
        data.sort(Comparator.comparing(RateForDate::getDate));
        var sorted = data.stream()
                .map(RateForDate::getRate)
                .toArray();
        assertArrayEquals(sorted, rates);
    }
}