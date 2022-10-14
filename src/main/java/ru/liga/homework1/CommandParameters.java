package ru.liga.homework1;

import ru.liga.homework1.enums.Algorithm;
import ru.liga.homework1.enums.Currency;
import ru.liga.homework1.enums.OutputType;
import ru.liga.homework1.enums.Period;

import java.time.LocalDate;
import java.util.List;

public class CommandParameters {

    private final List<Currency> currencies;
    private final Period period;
    private final LocalDate date;
    private final OutputType outputType;
    private final Algorithm algorithm;

    public CommandParameters(List<Currency> currencies, Period period, LocalDate date, Algorithm algorithm, OutputType outputType) {
        this.currencies = currencies;
        this.date = date;
        this.period = period;
        this.algorithm = algorithm;
        this.outputType = outputType;
    }

    public Period getPeriod() {
        return period;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public LocalDate getDate() {
        return date;
    }

    public OutputType getOutputType() {
        return outputType;
    }

    public Algorithm getAlgorithm() { return algorithm; }
}
