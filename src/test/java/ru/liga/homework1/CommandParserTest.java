package ru.liga.homework1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.liga.homework1.enums.*;
import ru.liga.homework1.exceptions.InvalidCommandException;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

class CommandParserTest {

    @DisplayName("Кидает исключение, когда команда не начинается с 'rate '")
    @Test
    void shouldThrowExceptionIfWrongBeginning() {
        String cmd = "rates USD -period week -alg myst";
        assertThrows(InvalidCommandException.class, () -> CommandParser.ParseCommand(cmd));
    }

    @DisplayName("Кидает исключение, когда неверно задана дата")
    @Test
    void shouldThrowExceptionIfWrongDate() {
        String cmd = "rate USD -date yesterday -alg myst";
        assertThrows(DateTimeParseException.class, () -> CommandParser.ParseCommand(cmd));
    }

    @DisplayName("Кидает исключение, когда дата задана в неверном формате")
    @Test
    void shouldThrowExceptionIfInvalidDate() {
        String cmd = "rate USD -date 01.02.23 -alg myst";
        assertThrows(DateTimeParseException.class, () -> CommandParser.ParseCommand(cmd));
    }

    @DisplayName("Кидает исключение, когда неверно задан период")
    @Test
    void shouldThrowExceptionIfWrongPeriod() {
        String cmd = "rate USD -period year -alg myst";
        assertThrows(InvalidCommandException.class, () -> CommandParser.ParseCommand(cmd));
    }

    @DisplayName("Кидает исключение, когда задана несуществующая валюта")
    @Test
    void shouldThrowExceptionIfWrongCurrency() {
        String cmd = "rate XXX -date tomorrow -alg myst";
        assertThrows(InvalidCommandException.class, () -> CommandParser.ParseCommand(cmd));
    }

    @DisplayName("Кидает исключение, когда неверно задан алгоритм")
    @Test
    void shouldThrowExceptionIfWrongAlgorithm() {
        String cmd = "rate USD -date tomorrow -alg unknown_algorithm";
        assertThrows(InvalidCommandException.class, () -> CommandParser.ParseCommand(cmd));
    }

    @DisplayName("Проверка парсинга одной валидной команды")
    @Test
    void checkValidCommandParsing() throws InvalidCommandException {
        String cmd = "rate USD,AMD -period month -alg myst -output graph";
        var params = CommandParser.ParseCommand(cmd);
        assertEquals(Algorithm.MYST, params.getAlgorithm());
        assertNull(params.getDate());
        assertEquals(Period.MONTH, params.getPeriod());
        assertEquals(OutputType.GRAPH, params.getOutputType());
        assertArrayEquals(new Currency[] {Currency.USD, Currency.AMD}, params.getCurrencies().toArray());
    }

    @DisplayName("Набор валидных вариантов команд")
    @Test
    void checkValidCommands() throws InvalidCommandException {
        CommandParser.ParseCommand("rate EUR -date tomorrow -alg linear");
        CommandParser.ParseCommand("rate USD -date tomorrow -alg linear -output list");
        CommandParser.ParseCommand("rate TRY -date tomorrow -alg lastyear");
        CommandParser.ParseCommand("rate BGN -date 01.01.2024 -alg myst");
        CommandParser.ParseCommand("rate USD,EUR -period week -alg myst -output graph");
        CommandParser.ParseCommand("rate EUR,AMD,BGN -period month -alg lastyear -output graph");
    }
}