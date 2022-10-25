package ru.liga.homework1.outputs;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.liga.homework1.enums.Currency;
import ru.liga.homework1.exceptions.InvalidOutputParametersException;
import ru.liga.homework1.model.CurrencyData;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ListOutputTest {

    @DisplayName("Кидает исключение, когда входные данные пусты")
    @Test
    void shouldThrowExceptionIfNoData() {
        var list = new ListOutput();
        assertThrows(InvalidOutputParametersException.class, () -> list.doOutput(new ArrayList<>()));
    }

    @DisplayName("Кидает исключение, когда передаются данные более чем по одной валюте")
    @Test
    void shouldThrowExceptionIfMoreThatOneCurrency() {
        var data = List.of(
                new CurrencyData(Currency.EUR, new ArrayList<>()),
                new CurrencyData(Currency.USD, new ArrayList<>()));
        var list = new ListOutput();
        assertThrows(InvalidOutputParametersException.class, () -> list.doOutput(data));
    }
}