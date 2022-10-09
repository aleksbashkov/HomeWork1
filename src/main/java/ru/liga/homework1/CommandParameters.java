package ru.liga.homework1;

import ru.liga.homework1.enums.Currency;
import ru.liga.homework1.enums.Period;

public class CommandParameters {

    private final Currency currency;
    private final Period period;

    public CommandParameters(Currency currency, Period period) {
        this.currency = currency;
        this.period = period;
    }

    public Period getPeriod() {
        return period;
    }

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return String.format("Currency: %s, Period: %s", currency, period);
    }
}
