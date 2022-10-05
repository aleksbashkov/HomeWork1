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

    /**
     * Парсит команду
     * @throws InvalidCommandException - кидает исключение InvalidCommandException в случае неверной команды
     */
    public static CommandParameters ParseCommand(String command) throws InvalidCommandException {
        var cmdEntities = command.split("\\s+");
        if (cmdEntities.length != 3 || !cmdEntities[ColRateCommandNum].equalsIgnoreCase("rate"))
            throw new InvalidCommandException("Неверная команда. Команда должна иметь вид 'rate <Currency> <Period>'");
        String currencyString = cmdEntities[ColCurrencyNum].toUpperCase();
        if (!Currency.checkCurrencyName(currencyString))
            throw new InvalidCommandException("Неизвестная валюта " + currencyString);
        String periodString = cmdEntities[ColPeriodNum].toUpperCase();
        if (!Period.checkPeriodName(periodString))
            throw new InvalidCommandException("Неизвестный период " + periodString);
        return  new CommandParameters(Currency.valueOf(currencyString), Period.valueOf(periodString));
    }
}
