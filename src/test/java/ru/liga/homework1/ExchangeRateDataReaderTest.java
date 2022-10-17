package ru.liga.homework1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExchangeRateDataReaderTest {

    @DisplayName("Кидает исключение, когда на вход подаём несуществующий файл")
    @Test
    void shouldThrowRuntimeException() {
        var reader = new ExchangeRateDataReader();
        assertThrows(RuntimeException.class, () -> reader.readExchangeData("SomeNonexistentFile.csv"));
    }

    @DisplayName("Проверяем, что в заданном файле 5 записей")
    @Test
    void checkFiveRecordsInFile() {
        var reader = new ExchangeRateDataReader();
        var data = reader.readExchangeData("EUR_5_records.csv");
        assertEquals(5, data.size());
    }
}