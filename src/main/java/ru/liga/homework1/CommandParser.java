package ru.liga.homework1;

import ru.liga.homework1.Enum.Currency;
import ru.liga.homework1.Enum.Period;

import java.security.InvalidParameterException;
import java.util.Arrays;

/**
 * Разбирает введённую пользователем строку.
 * Может кидать unchecked-исключения InvalidParameterException.
 */
public class CommandParser {

    private final Currency currency;
    private final Period period;

    public CommandParser(String command) {
        var cmdEntities = command.split(" ");
        if (cmdEntities == null || cmdEntities.length != 3 || !cmdEntities[0].equals("rate"))
            throw new InvalidParameterException("Invalid command");
        if (!Arrays.stream(Currency.values()).anyMatch(cur -> cur.name().equals(cmdEntities[1])))
            throw new InvalidParameterException("Unknown currency " + cmdEntities[1]);
        currency = Currency.valueOf(cmdEntities[1]);
        if (!Arrays.stream(Period.values()).anyMatch(per -> per.name().toLowerCase().equals(cmdEntities[2])))
            throw new InvalidParameterException("Unknown period " + cmdEntities[2]);
        period = Period.valueOf(cmdEntities[2].toUpperCase());
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
