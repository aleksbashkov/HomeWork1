package ru.liga.homework1;

import ru.liga.homework1.Enums.Currency;
import ru.liga.homework1.Enums.Period;

public class CommandParameters {
    public final Currency currency;
    public final Period period;

    public CommandParameters(Currency currency, Period period) {
        this.currency = currency;
        this.period = period;
    }
}
