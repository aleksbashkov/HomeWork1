package ru.liga.homework1;

import ru.liga.homework1.Enums.Currency;
import ru.liga.homework1.Enums.Period;
import ru.liga.homework1.Exceptions.InvalidCommandException;

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
        if (cmdEntities.length != 3 || !cmdEntities[ColRateCommandNum].equalsIgnoreCase("rate"))
            throw new InvalidCommandException("Неверная команда. Команда должна иметь вид 'rate <Currency> <Period>'");
        String currencyString = cmdEntities[ColCurrencyNum].toUpperCase();
        if (!Currency.checkCurrencyName(currencyString))
            throw new InvalidCommandException("Неизвестная валюта " + currencyString);
        currency = Currency.valueOf(currencyString);
        String periodString = cmdEntities[ColPeriodNum].toUpperCase();
        if (!Period.checkPeriodName(periodString))
            throw new InvalidCommandException("Неизвестный период " + periodString);
        period = Period.valueOf(periodString);
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
