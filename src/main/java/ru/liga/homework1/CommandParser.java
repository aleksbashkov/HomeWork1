package ru.liga.homework1;

import ru.liga.homework1.Enums.Currency;
import ru.liga.homework1.Enums.Period;
import ru.liga.homework1.Exceptions.InvalidCommandException;

import java.util.Arrays;

/**
 * Разбирает введённую пользователем строку.
 */
public class CommandParser {

    private static final int ColRateCommandNum = 0;
    private static final int ColCurrencyNum = 1;
    private static final int ColPeriodNum = 2;
    private final Currency currency;
    private final Period period;

    public CommandParser(String command) throws InvalidCommandException {
        var cmdEntities = command.split("\\s+");
        if (cmdEntities == null || cmdEntities.length != 3 || !cmdEntities[ColRateCommandNum].toLowerCase().equals("rate"))
            throw new InvalidCommandException("Invalid command. Valid command is 'rate <Currency> <Period>'");
        String currencyString = cmdEntities[ColCurrencyNum];
        if (Arrays.stream(Currency.values()).noneMatch(cur -> cur.name().equals(currencyString.toUpperCase())))
            throw new InvalidCommandException("Unknown currency " + currencyString);
        currency = Currency.valueOf(currencyString.toUpperCase());
        String periodString = cmdEntities[ColPeriodNum];
        if (Arrays.stream(Period.values()).noneMatch(per -> per.name().equals(periodString.toUpperCase())))
            throw new InvalidCommandException("Unknown period " + periodString);
        period = Period.valueOf(periodString.toUpperCase());
    }

    /**
     * Возвращает валюту
     * @return заданная пользователем валюта
     */
    public Currency getCurrency() {
        return currency;
    }

    /**
     * Возвращает период прогноза
     * @return заданный пользователем период прогноза
     */
    public  Period getPeriod() {
        return period;
    }
}
